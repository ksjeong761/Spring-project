#!/usr/bin/python
import device_status as ds
import requests
import time

while(True):
    json=ds.DeviceStatus().to_JSON()
    f = open("log.txt", "w")
    f.write(json)
    f.close()
    print(json)

    response = requests.post(url = 'http://localhost:8080/devices/statuses', json=json)
    print(response.url)
    print(response.status_code)
    print(response.headers)
    print(response.text)

    #time.sleep(5)
    break