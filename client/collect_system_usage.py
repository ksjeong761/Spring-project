#!/usr/bin/python
import psutil
import time
import json
from collections import OrderedDict

#프로그램 실행할 때 고정값 요청하고 주기적으로 보낼 때는 고정값 제외하기
#OS별로 데이터가 다르니까 세부 항목을 미리 정의하는건 좋은 선택이 아님 dict로 받기

class cpu:
    def __init__(self):
        self.times = psutil.cpu_times(percpu=False)._asdict()
        self.stats = psutil.cpu_stats()._asdict() #계속 누적되는 데이터라서 가공 필요
        self.loadavg = dict(zip(['minute_5', 'minute_10', 'minute_15'], psutil.getloadavg()))
        #print(psutil.cpu_percent(interval=None, percpu=True)) #퍼센트는 계산해주면 되니까 빼도 됨
        #print(psutil.cpu_times_percent(interval=None, percpu=False)) #interval 올리면 그만큼 cpu 블록되고나서 데이터 보냄
        #print(psutil.cpu_count(logical=True))  #고정값 “logical CPUs” means the number of physical cores multiplied by the number of threads that can run on each core (this is known as Hyper Threading). If logical is False return the number of physical cores only
        #print(psutil.cpu_freq(percpu=False)) #cpu 주파수 변할일이 있는지 없는지부터 조사
        return
    
    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__, indent=4)

while(1):
    time.sleep(3)
    print('--------------')
    print(cpu().toJSON())

    # sys_data["memory"] = {
    #     psutil.virtual_memory(),
    #     psutil.swap_memory()
    # }
    # sys_data["disk"] = {
    #     #psutil.disk_partitions(all=False),
    #     #psutil.disk_usage(path),
    #     psutil.disk_io_counters(perdisk=False, nowrap=True)
    # }
    # # sys_data["network"] = {
    # #     # psutil.net_io_counters(pernic=False, nowrap=True),
    # #     # psutil.net_connections(kind='inet'),
    # #     # psutil.net_if_addrs(),
    # #     # psutil.net_if_stats()
    # # }
    # # sys_data["sensor"] = {
    # #     psutil.sensors_temperatures(fahrenheit=False),
    # #     psutil.sensors_fans(),
    # #     psutil.sensors_battery()
    # # }
    # sys_data["time"] = {
    #     psutil.boot_time()
    # }
    # # sys_data["user"] = {
    # #     # psutil.users()
    # # }
    # sys_data["process"] = {
    #     #psutil.pids(),
    #     psutil.process_iter(attrs=None, ad_value=None),
    #     #psutil.pid_exists(pid),
    #     #psutil.wait_procs(procs, timeout=None, callback=None)
    # }