#!/usr/bin/python
import psutil
import time
import json
from collections import OrderedDict

#[TODO]
#고정값은 프로그램 실행할 때 얻어오고 주기적으로 보낼 때는 제외하기
#클래스 구조 난잡하니까 더 간단하게 바꾸기
#리스트도 JSON으로 바꿀 수 있게 하기
#디스크 상세 추가하기
#프로세스 상세 추가하기

#OS별로 데이터가 다르니까 세부 항목을 미리 정의하는건 좋은 선택이 아님 dict로 받기
class Cpu:
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

class Memory:
    def __init__(self):
        self.virtual_memory = psutil.virtual_memory()._asdict()
        self.swap_memory = psutil.swap_memory()._asdict() # total이 used보다 작게 나와서 버그생김
        return

    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__, indent=4)

class Disk:
    def __init__(self):
        #self.partitions = psutil.disk_partitions(all=False)._asdict() #리스트는 나중에
        self.disk_usage = psutil.disk_usage('/')._asdict() #일단 루트만
        self.disk_io_counters = psutil.disk_io_counters(perdisk=False, nowrap=True)._asdict()
        return

    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__, indent=4)

class Network:
    def __init__(self):
        self.net_io_counters = psutil.net_io_counters(pernic=False, nowrap=True)._asdict()
        #self.net_connections = psutil.net_connections(kind='inet')._asdict()
        self.net_if_addrs = psutil.net_if_addrs() #고정값 네트워크 정보는 고정되어 있는 경우가 대부분이지만 바뀔 수도 있음
        self.net_if_stats = psutil.net_if_stats() #유니코드 문자 처리 해야됨
        return

    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__, indent=4)

class Sensor:
    def __init__(self):
        #self.sensors_temperatures = psutil.sensors_temperatures(fahrenheit=False) # 감지 안 됨
        #self.sensors_fans = psutil.sensors_fans() # 감지 안 됨
        self.sensors_battery = psutil.sensors_battery()._asdict() #노트북에선 되는데 배터리 없을 경우 테스트 필요
        return

    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__, indent=4)

class TimeSpent:
    def __init__(self):
        self.time = dict({'boot_time' : psutil.boot_time()})
        return

    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__, indent=4)

class User:
    def __init__(self):
        #self.users = psutil.users()  #리스트는 나중에
        return

    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__, indent=4)

class Process:
    def __init__(self):
        self.processes = psutil.process_iter()
        for p in self.processes:
            print(p)

        #psutil.pid_exists(pid),
        #psutil.wait_procs(procs, timeout=None, callback=None)
        return

    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__, indent=4)

while(1):
    time.sleep(3)
    print('--------------')
    #print(Cpu().toJSON())
    #print(Memory().toJSON())
    #print(Disk().toJSON())
    #print(Network().toJSON())
    #print(Sensor().toJSON())
    #print(TimeSpent().toJSON())
    #print(User().toJSON())
    print(Process())