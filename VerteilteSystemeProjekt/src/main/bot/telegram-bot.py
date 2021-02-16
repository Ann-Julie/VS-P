"""
@author: Ann-Julie Sinnaeve
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
# needed for our group chat, number is only refering to this chat, not other group chats
vSysChatId = "-507253319"

# needed for avg increase last n days
timeDurationUserInput = range(1)

# to send messages to the bot, the token and group chat id are needed
bot_send_url = "http://api.telegram.org/bot" + vSysBotToken + "/sendMessage"
port_number = 4567

# getURL = "http://localhost:" + str(portNumber)
get_api_url = "http://167.99.252.170:" + str(port_number)


def start(update, context):
    # message when bot chat gets started
    update.message.reply_text(
        bot_descriptions.startCommandText)


def help(update, context):
    update.message.reply_text(bot_descriptions.helpCommandText)


def fetch_value_from_json(chatId, message, key):
    message_as_json = json.loads(message)
    json_value = message_as_json[key]
    return str(round(json_value))


# call all methods
def all_data(update, context):
    update.message.reply_text
    total_infections(update, context)
    new_infections_from_last_twenty_four_hours(update, context)
    target_total_infection(update, context)
    forecast_necessary_lockdown_days(update, context)
    incidence_value_last_seven_days(update, context)
    average_increase_last_60_days(update, context)
    increase_last_twenty_four_hours(update, context)


# needed for all data
def average_increase_last_60_days(update, context):
    update.message.reply_text(
        'Durchschnittlicher Anstieg bzw. Abstieg der letzten 60 Tage: ' + fetch_value_from_json(update.effective_message.chat_id,
                                                                                                get_average_increase_last_n_days(60).text,
                                                                                                "averageIncreaseLastNDays"))


def total_infections(update, context):
    update.message.reply_text('Aktuelle Gesamtinfektionen in Deutschland: ' + fetch_value_from_json(update.effective_message.chat_id, get_total_infections().text, "totalInfections"))


def new_infections_from_last_twenty_four_hours(update, context):
    update.message.reply_text('Zahl Neuinfektionen der letzten 24 Stunden: ' + fetch_value_from_json(update.effective_message.chat_id,
                                                                                                     get_new_infections_from_last_twenty_four_hours().text, "newInfectionsLastTwentyFourHours"))


def target_total_infection(update, context):
    update.message.reply_text('Ziel Gesamtinfektion für Zielinzidenzwert (35): ' + fetch_value_from_json(update.effective_message.chat_id,
                                                                                                         get_target_total_infection().text, "targetTotalInfection"))


def forecast_necessary_lockdown_days(update, context):
    update.message.reply_text('Vorhersage der nötigen Tage im Lockdown: ' + fetch_value_from_json(update.effective_message.chat_id,
                                                                                                  get_forecast_necessary_lockdown_days().text, "forecastNecessaryLockdownDays"))


def incidence_value_last_seven_days(update, context):
    update.message.reply_text('Inzidenzwert der letzten 7 Tage: ' + fetch_value_from_json(update.effective_message.chat_id,
                                                                                          get_incidence_value_last_seven_days().text, "incidenceValueLastSevenDays"))


# wo den Text einbauen
def average_increase_last_n_days(update, context):
    user_input = update.message.text
    if (2 <= int(user_input) <= 90):
        update.message.reply_text("Durchschnittlicher Anstieg bzw Abstieg der letzten " + user_input + " Tage: " +
                                  fetch_value_from_json(update.effective_message.chat_id, get_average_increase_last_n_days(user_input).text, "averageIncreaseLastNDays"))
        context.bot.send_message(update.effective_message.chat_id,
                                 'Geben Sie ein neues Intervall zwischen 2 bis 90 an oder beenden Sie den Abfrage Modus mit /cancel.')

    else:
        update.message.reply_text('Dieser Bot gibt Werte für 2 bis 90 Tage an, bitte versuchen Sie es erneut oder beenden Sie den Abfrage Modus mit /cancel')


def increase_last_twenty_four_hours(update, context):
    update.message.reply_text('Anstieg bzw. Abstieg der letzten 24 Stunden: ' + fetch_value_from_json(update.effective_message.chat_id,
                                                                                                      get_increase_last_twentyFour_hours().text, "increaseLastTwentyFourHours"))


def end_user_input(update, context):
    update.message.reply_text('Please type in new command. See /help for command list')
    return ConversationHandler.END


# method needed to send messages to the group chat
def send_message(chatId, message):
    print("test")
    send_text = requests.post(bot_send_url + "?chat_id=" + str(chatId) + "&text=" + message)
    # this is needed so we won't get response [200] but the json data
    data_raw = requests.get(send_text).text
    return data_raw

def get_all_data():
    endPoint = "/alldata"
    return requests.get(get_api_url + endPoint)


def get_total_infections():
    endPoint = "/totalinfections"
    return requests.get(get_api_url + endPoint)


def get_new_infections_from_last_twenty_four_hours():
    endPoint = "/newinfectionsfromlasttwentyfourhours"
    return requests.get(get_api_url + endPoint)


def get_target_total_infection():
    endPoint = "/targettotalinfection"
    return requests.get(get_api_url + endPoint)


def get_forecast_necessary_lockdown_days():
    endPoint = "/forecastnecessarylockdowndays"
    return requests.get(get_api_url + endPoint)


def get_incidence_value_last_seven_days():
    endPoint = "/incidencevaluelastsevendays"
    return requests.get(get_api_url + endPoint)


def get_average_increase_last_n_days(days):
    endPoint = "/averageincreaselastndays/"
    return requests.get(get_api_url + endPoint + str(days))


def get_increase_last_twentyFour_hours():
    endPoint = "/increaselasttwentyfourhours"
    return requests.get(get_api_url + endPoint)


def cancel_query_mode(update, context):
    update.message.reply_text('Ende des Abfrage Modus des durchschnittlichen Anstiegs bzw. Abstiegs der ausgewählten Tage.')
    return ConversationHandler.END


def prompt_user_input(update, context):
    update.message.reply_text('Bitte geben Sie eine Zahl zwischen 2 bis 90 an: ')
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
        states={timeDurationUserInput: [RegexHandler('^[0-9]+$', average_increase_last_n_days)]},
        fallbacks=[CommandHandler('cancel', cancel_query_mode)]
    )
    dp.add_handler(conv_handler)

    # Start the Bot
    updater.start_polling()

    # Run the bot until you press Ctrl-C or the process receives SIGINT,
    updater.idle()


if __name__ == '__main__':
    main()
