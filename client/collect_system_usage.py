#!/usr/bin/python
import device_status as ds
import requests

response = requests.post(url = 'http://localhost:8080/receiveJSON', json=ds.DeviceStatus().to_JSON())
print(response.url)
print(response.status_code)
print(response.headers)
print(response.text)