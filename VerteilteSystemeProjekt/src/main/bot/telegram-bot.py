"""
Um die Sachen im Browser anzuschauen, api.telegram.org/bot mit TokenNr /getUpdates aufrufen

Wenn wir in der App bspw einen Text eingeben (natürlich im bot selbst) und die Seite im Browser neu laden, können
wir uns den Text anzeigen lassen

wir wollen mit dem bot diese rest api wrappen
dafür schreiben wir für jeden Zugriffspunkt eine Methode, die den jeweiligen Wert ausgibt

"""

import requests
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
#bot_chat_id = ["message"]["chat"]["id"]

def start(update, context):
    # message when bot chat gets started
    update.message.reply_text('Um eine vollständige Liste aller Befehle zu erhalten, '
                                                                    '/help eingeben')

def help(update, context):
        # message when /help is typed in
        update.message.reply_text('Mögliche Befehle: '
                                  '/alldata')

#def all_data(update: Update, context: CallbackContext) -> int:
    # method get_all_data() is called
    #update.message.reply_text("Alle Daten: ")


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


def get_new_infections_from_last_twentyFour_hours():
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


# send_message(vSysChatId, str(get_average_increase_last_n_days(5)).text)
# send_message(vSysChatId, "test")

# print(get_all_data().text)
# send_message(vSysChatId, get_all_data().text)
# send_message(vSysChatId, get_increase_last_twentyFour_hours().text)

# on different commands - answer in Telegram
def main():
    """Start the bot."""
    # Create the Updater and pass it your bot's token.
    # Make sure to set use_context=True to use the new context based callbacks
    updater = Updater("1668868793:AAF5cLabcsGgRHK8ovTbTnMuJ7nMzQH-oGQ", use_context=True)

    # Get the dispatcher to register handlers
    dp = updater.dispatcher

    # set up commands
    dp.add_handler(CommandHandler("start", start))
    dp.add_handler(CommandHandler("help", help))

    # commands for functionality of covid numbers
   # dp.add_handler(CommandHandler("all_data"), all_data)

    # Start the Bot
    updater.start_polling()

    # Run the bot until you press Ctrl-C or the process receives SIGINT,
    updater.idle()


if __name__ == '__main__':
    main()

""" 
todo morgen: Commands anschauen für Interaktion mit User in App und ganz viele if else ;) 
json parser python 

poll benötigt um die Anzahl der Tage später anzugeben

"""






