"""
Um die Sachen im Browser anzuschauen, api.telegram.org/bot mit TokenNr /getUpdates aufrufen

Wenn wir in der App bspw einen Text eingeben (natürlich im bot selbst) und die Seite im Browser neu laden, können
wir uns den Text anzeigen lassen

wir wollen mit dem bot diese rest api wrappen
dafür schreiben wir für jeden Zugriffspunkt eine Methode, die den jeweiligen Wert ausgibt

"""

import requests
import json
from telegram import Update
from telegram.ext import (
    Updater,
    CommandHandler,
    PollAnswerHandler,
    PollHandler,
    MessageHandler,
    Filters,
    CallbackContext,
)

vSysBotToken = "1668868793:AAF5cLabcsGgRHK8ovTbTnMuJ7nMzQH-oGQ"
vSysChatId = "-507253319"

# to send messages to the bot, the token and group chat id are needed
sendURL = "http://api.telegram.org/bot" + vSysBotToken + "/sendMessage"
portNumber = 4567

# getURL = "http://localhost:" + str(portNumber)
getURL = "http://167.99.252.170:" + str(portNumber)

# buttons die von /start an angezeigt werden und mit denen die gewünschten Funktionen ausgewählt werden können
button_all_data = {'text': 'All data', 'callback_data': 'All data'}
button_total_infections = {'text': 'Total infections', 'callback_data': 'All data'}
# eigentlich dictionary, wird als Array aufgelistet
button_array = {'inline_keyboard': [[button_all_data, button_total_infections]]}


def start(update, context):
    # message when bot chat gets started
    update.message.reply_text(
        'Bitte gewünschte Anfrage auswählen. Möglichkeiten und Funktionen werden mit /help erläutert: ')


def help(update, context):
    # message when /help is typed in
    update.message.reply_text('Auswahl: '
                              '/alldata  , /totalinfections ,'
                              '/newinfectionsfromlast24hours , /targettotalinfections'
                              '/forecastnecessarylockdowndays , /incidencevaluelastsevendays ',
                              '/averageincreaselastndays , /increaselast24hours'
                              )


def send_buttons(chatId, message, buttonJSON):
    send_text = requests.post(sendURL + "?chat_id=" + str(chatId) + "&reply_markup" + buttonJSON + "&text=" + message)


# prüfen ob unsere einkommende Nachricht ein callback von einem Button ist
def is_callback(dict):
    # callback_query ist der zweite Schlüssel in result
    if ['callback_query'] in dict:
        return True


def all_data(update, context):
    update.message.reply_text('All data: ' + send_message(update.effective_message.chat_id, get_all_data().text))
    # update.message.reply_text(send_message(update.effective_message.chat_id, get_all_data().text))


def total_infections(update, context):
    update.message.reply_text(
        'Total Infections: ' + send_message(update.effective_message.chat_id, get_total_infections().text))
    # update.message.reply_text(send_message(update.effective_message.chat_id, get_all_data().text))


def new_infections_from_last_twenty_four_hours(update, context):
    update.message.reply_text('Target total infections: ' + send_message(update.effective_message.chat_id,
                                                                         get_new_infections_from_last_twenty_four_hours().text))


def target_total_infection(update, context):
    update.message.reply_text('Target total infections: ' + send_message(update.effective_message.chat_id,
                                                                         get_target_total_infection().text))


def forecast_necessary_lockdown_days(update, context):
    update.message.reply_text('Forecast lockdown days: ' + send_message(update.effective_message.chat_id,
                                                                        get_forecast_necessary_lockdown_days().text))


def incidence_value_last_seven_days(update, context):
    update.message.reply_text('Incidence value last 7 days: ' + send_message(update.effective_message.chat_id,

                                                                             get_incidence_value_last_seven_days().text))


# Logik weiter machen morgen
def average_increase_last_n_days(update, context):
    update.message.reply_text('Type in number of days between x to x days: ')
    # hier dann auf eingabe warten und prüfen ob Zahl eingegeben wurde und im Fenster zwischen x und x liegt

    # hier dann eine variable übergeben, die den user input ausgibt
    update.message.reply_text(send_message(update.effective_message.chat_id,
                                           get_average_increase_last_n_days().text))


def increase_last_twenty_four_hours(update, context):
    update.message.reply_text('Increase last 24 hours: ' + send_message(update.effective_message.chat_id,
                                                                        get_increase_last_twentyFour_hours().text))


# method needed to send messages to the group chat
def send_message(chatId, message):
    send_text = requests.post(sendURL + "?chat_id=" + str(chatId) + "&text=" + message)
    # this is needed so we won't get response [200] but the json data
    data_raw = requests.get(send_text).text
    return data_raw


def get_all_data():
    endPoint = "/alldata"
    return requests.get(getURL + endPoint)


def get_total_infections():
    endPoint = "/totalinfections"
    return requests.get(getURL + endPoint)


def get_new_infections_from_last_twenty_four_hours():
    endPoint = "/newinfectionsfromlasttwentyfourhours"
    return requests.get(getURL + endPoint)


def get_target_total_infection():
    endPoint = "/targettotalinfection"
    return requests.get(getURL + endPoint)


def get_forecast_necessary_lockdown_days():
    endPoint = "/forecastnecessarylockdowndays"
    return requests.get(getURL + endPoint)


def get_incidence_value_last_seven_days():
    endPoint = "/incidencevaluelastsevendays"
    return requests.get(getURL + endPoint)


def get_average_increase_last_n_days(days):
    endPoint = "/averageincreaselastndays/"
    return requests.get(getURL + endPoint + str(days))


def get_increase_last_twentyFour_hours():
    endPoint = "/increaselasttwentyfourhours"
    return requests.get(getURL + endPoint)


def main():
    # pass Updater our bot token
    updater = Updater("1668868793:AAF5cLabcsGgRHK8ovTbTnMuJ7nMzQH-oGQ")

    # Get the dispatcher to register handlers
    dp = updater.dispatcher

    # send_buttons(vSysChatId, " ", json.dumps(button_array))

    # set up commands
    dp.add_handler(CommandHandler("start", start))
    dp.add_handler(CommandHandler("help", help))

    # commands for functionality of covid numbers
    dp.add_handler(CommandHandler("alldata", all_data))
    dp.add_handler(CommandHandler("totalinfections", total_infections))
    dp.add_handler(CommandHandler("targettotalinfections", target_total_infection))
    dp.add_handler(CommandHandler("forecastnecessarylockdowndays", forecast_necessary_lockdown_days))
    dp.add_handler(CommandHandler("incidencevaluelastsevendays", incidence_value_last_seven_days))
    dp.add_handler(CommandHandler("newinfectionsfromlast24hours", new_infections_from_last_twenty_four_hours))
    dp.add_handler(CommandHandler("averageincreaselastndays", average_increase_last_n_days))
    dp.add_handler(CommandHandler("increaselast24hours", increase_last_twenty_four_hours))

    # Start the Bot
    updater.start_polling()

    # Run the bot until you press Ctrl-C or the process receives SIGINT,
    updater.idle()


if __name__ == '__main__':
    main()

""" 
TODO:
Buttons einbinden optional
json parser python 

poll benötigt um die Anzahl der Tage später anzugeben

"""
