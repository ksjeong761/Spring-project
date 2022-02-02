#!/usr/bin/python
import device_status as ds
import requests

j = ds.DeviceStatus().toJSON()
#print(j)

URL='http://localhost:8080/receiveJSON'
response = requests.get(URL)
print(response)