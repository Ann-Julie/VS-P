"""
This class wraps the REST API so users can get all their requested data by using commands
When starting the bot, the user gets information about how to use it and which functions are
available with a command list
"""
import json

import requests
import bot_descriptions
from telegram.ext import (
    Updater, CommandHandler, MessageHandler, Filters, ConversationHandler, RegexHandler
)

vSysBotToken = "1668868793:AAF5cLabcsGgRHK8ovTbTnMuJ7nMzQH-oGQ"
# needed for our group chat, number is only reffering to this chat, not other group chats
vSysChatId = "-507253319"

timeDurationUserInput = range(1)

# to send messages to the bot, the token and group chat id are needed
sendURL = "http://api.telegram.org/bot" + vSysBotToken + "/sendMessage"
portNumber = 4567

# getURL = "http://localhost:" + str(portNumber)
getURL = "http://167.99.252.170:" + str(portNumber)


def start(update, context):
    # message when bot chat gets started
    update.message.reply_text(
       bot_descriptions.startCommandText)


def help(update, context):
    # message when /help is typed in
   # update.message.reply_text(bot_descriptions.helpCommandText)
    update.message.reply_text(bot_descriptions.helpCommandText)
    """
    Auswahl: 
    Alle Informationen:
    /alldata 
    Aktuelle Gesamtinfektion in Deutschland:
    /totalinfec
    Zahl Neuinfektionen letzte 24 Stunden:
    /newinfeclast24h
    Zielinzidenzwert der Gesamt Infektionen:
    /targettotalinfec
    Annahme Tage in Lockdown:
    /forecastlockdays
    Inzidenzwert der  letzten 7 Tage:
    /incvallast7days 
    Durchschnittlicher Anstieg 
    der letzten n Tage:
    /avgincrlastndays
    Anstieg der letzten 24 Stunden:
    /incrlast24h
    """

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


def process_user_input(update, context):
    user_input = update.message.text
    if (2 <= int(user_input) <= 90):
        update.message.reply_text(get_average_increase_last_n_days(user_input).text)
        send_message(update.effective_message.chat_id, 'Input another time interval between 2 and 90 days or abort query mode with /cancel.')

    else:
        update.message.reply_text('Try again with a number between 2 to 90 days.')


def end_user_input(update, context):
    update.message.reply_text('Please type in new command. See /help for command list')
    return ConversationHandler.END


def increase_last_twenty_four_hours(update, context):
    update.message.reply_text('Increase last 24 hours: ' + send_message(update.effective_message.chat_id,
                                                                        get_increase_last_twentyFour_hours().text))

"""

# some JSON:
x =  '{ "name":"John", "age":30, "city":"New York"}'

# parse x:
y = json.loads(x)

# the result is a Python dictionary:
print(y["age"]) 
"""

# method needed to send messages to the group chat
def send_message(chatId, message):
    send_text = requests.post(sendURL + "?chat_id=" + str(chatId) + "&text=" + message)
    # this is needed so we won't get response [200] but the json data
    data_raw = requests.get(send_text).text
    parseJson = json.loads(data_raw)
    return parseJson


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


def cancel_query_mode(update, context):
    update.message.reply_text('Ending Average Case Increase query mode.')
    return ConversationHandler.END


def prompt_user_input(update, context):
    update.message.reply_text('Please define a time interval between 2 to 90 days: ')
    return timeDurationUserInput


def main():


    # pass Updater our bot token
    updater = Updater("1668868793:AAF5cLabcsGgRHK8ovTbTnMuJ7nMzQH-oGQ")

    # Get the dispatcher to register handlers
    dp = updater.dispatcher

    # set up commands
    dp.add_handler(CommandHandler("start", start))
    dp.add_handler(CommandHandler("help", help))

    # commands for functionality of covid numbers
    dp.add_handler(CommandHandler("alldata", all_data))
    dp.add_handler(CommandHandler("totalinfec", total_infections))
    dp.add_handler(CommandHandler("targettotalinfec", target_total_infection))
    dp.add_handler(CommandHandler("forecastlockdays", forecast_necessary_lockdown_days))
    dp.add_handler(CommandHandler("incvallast7days", incidence_value_last_seven_days))
    dp.add_handler(CommandHandler("newinfeclast24h", new_infections_from_last_twenty_four_hours))
    dp.add_handler(CommandHandler("incrlast24h", increase_last_twenty_four_hours))

    conv_handler = ConversationHandler(
        entry_points=[CommandHandler("avgincrlastndays", prompt_user_input)],
        states={timeDurationUserInput: [RegexHandler('^[0-9]+$', process_user_input)]},
        fallbacks=[CommandHandler('cancel', cancel_query_mode)]
    )
    dp.add_handler(conv_handler)

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

"""
