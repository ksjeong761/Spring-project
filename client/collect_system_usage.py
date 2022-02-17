#!/usr/bin/python
import device_status as ds
import requests
import time

response = requests.post(
    url = 'http://localhost:8080/users'
)

print(response.url)
print(response.status_code)
print(response.headers)
print(response.text)

response = requests.post(
    url = 'http://localhost:8080/devices'
)

print(response.url)
print(response.status_code)
print(response.headers)
print(response.text)

delay = 5
run_count = 1000

while(run_count > 0):
    run_count -= 1
    print(ds.DeviceStatus().to_JSON())

    response = requests.post(
        url = 'http://localhost:8080/devices/statuses',
        headers={'Content-Type':'application/json; charset=utf-8'},
        data=ds.DeviceStatus().to_JSON().encode('utf-8')
    )

    print(response.url)
    print(response.status_code)
    print(response.headers)
    print(response.text)

    time.sleep(delay)
    #break