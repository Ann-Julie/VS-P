"""
 Um die Sachen im Browser anzuschauen, api.telegram.org/bot mit TokenNr /getUpdates aufrufen
 Wenn wir in der App bspw einen Text eingeben (natürlich im bot selbst) und die Seite im Browser neu laden, können
 wir uns den Text anzeigen lassen
"""
from urllib import request

import requests

# Link benötigt
vSysBotToken = "1668868793:AAF5cLabcsGgRHK8ovTbTnMuJ7nMzQH-oGQ"
vSysChatId = "-507253319"

# Nachrichten versenden
# neue URL benötigt
sendURL = "http://api.telegram.org/bot" + vSysBotToken + "/sendMessage"
# url für lokal host für die api später
portNumber = 4567

# get URL ist dann später für den Server, also mit lokal host austauschen
# getURL = "http://localhost:" + str(portNumber)
getURL = "http://167.99.252.170:" + str(portNumber)

def send_message(chatId, message):
    # die Ergebnisse aus den Variablen unten senden
    requests.post(sendURL + "?chat_id=" + str(chatId) + "&text=" + message)


"""
wir wollen mit dem bot diese rest api wrappen
dafür schreiben wir für jeden Zugriffspunkt eine Methode, die den jeweiligen Wert ausgibt
"""

def get_all_data():
    endPoint = "/alldata"
    return requests.get(getURL + endPoint)

def get_average_increase_last_n_days(days):
    # über commands soll eine Abfrage erscheinen, nach Tagen
    #
    endPoint = "/averageincreaselastndays/"
    return requests.get(getURL + endPoint + str(days))


send_message(vSysChatId, str(get_average_increase_last_n_days(5)))
# so bekommen wir nur das request Objekt und müssen jetzt noch die
# Daten daraus herausholen
r = get_average_increase_last_n_days(5)
print(r)
print(r.text)
get_average_increase_last_n_days(5)
""" 
ab dem Zeitpunkt an dem es einen Server gibt, muss einfach nur die URL in getURL angepasst werden, feddich
in den Chat mit send_Message einfügen, also nur überlegen wie Text von  A nach B kommt, jedesMal wenn Zugriffspunkt 
aufgerufen wird
todo morgen: Commands anschauen für Interaktion mit User in App
"""






