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

def send_message(chatId, message):
    # die Ergebnisse aus den Variablen unten senden
    requests.post(sendURL + "?chat_id=" + str(chatId) + "&text=" + message)

send_message(vSysChatId, "Welcome to this channel")





