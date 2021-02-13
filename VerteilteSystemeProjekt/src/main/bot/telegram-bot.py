"""
 Um die Sachen im Browser anzuschauen, api.telegram.org/bot mit TokenNr /getUpdates aufrufen
 Wenn wir in der App bspw einen Text eingeben (natürlich im bot selbst) und die Seite im Browser neu laden, können
 wir uns den Text anzeigen lassen
"""
from urllib import request

import requests

vSysBotToken = "1668868793:AAF5cLabcsGgRHK8ovTbTnMuJ7nMzQH-oGQ"
vSysChatId = "-507253319"

# to send messages to the bot, the token and group chat id are needed
sendURL = "http://api.telegram.org/bot" + vSysBotToken + "/sendMessage"
portNumber = 4567

# getURL = "http://localhost:" + str(portNumber)
getURL = "http://167.99.252.170:" + str(portNumber)

def extract_result(dict):
    result_array = dict['result']

    #prüfen ob Array leer ist

# method needed to send messages to the group chat
def send_message(chatId, message):
    send_text = requests.post(sendURL + "?chat_id=" + str(chatId) + "&text=" + message)
    data_raw = requests.get(send_text)
    data = data_raw.json()
    print(data)


"""

wir wollen mit dem bot diese rest api wrappen
dafür schreiben wir für jeden Zugriffspunkt eine Methode, die den jeweiligen Wert ausgibt
"""

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

r = send_message(vSysChatId, str(get_average_increase_last_n_days(5)))
# send_message(vSysChatId, "was geht ab")
print(r.text)

""" 
ab dem Zeitpunkt an dem es einen Server gibt, muss einfach nur die URL in getURL angepasst werden, feddich
in den Chat mit send_Message einfügen, also nur überlegen wie Text von  A nach B kommt, jedesMal wenn Zugriffspunkt 
aufgerufen wird
todo morgen: Commands anschauen für Interaktion mit User in App
"""






