#!/usr/bin/python
import psutil
import json
from collections import OrderedDict, namedtuple

#[TODO]
#고정값은 프로그램 실행할 때 얻어오고 주기적으로 보낼 때는 제외하기
#예외 처리(센서 등 github 참고)

class DeviceStatus:
    def __init__(self):
        self.cpu = self.Cpu()
        self.memory = self.Memory()
        self.disk = self.Disk()
        self.network = self.Network()
        self.sensor = self.Sensor()
        self.timeSpent = self.TimeSpent()
        self.user = self.User()
        self.process = self.Process()
        self.win_service = self.WinService()

    class Cpu:
        def __init__(self):
            self.cpu_times = psutil.cpu_times(percpu=False) #계속 누적되는 데이터라서 가공 필요
            self.cpu_stats = psutil.cpu_stats() #계속 누적되는 데이터라서 가공 필요
            self.cpu_loadavg = OrderedDict(zip(['minute_1', 'minute_5', 'minute_15'], psutil.getloadavg()))
            self.cpu_percent = psutil.cpu_percent(interval=None, percpu=False)
            self.cpu_times_percent = psutil.cpu_times_percent(interval=None, percpu=False)
            self.cpu_count = psutil.cpu_count(logical=True) #고정값
            #self.cpu_freq = psutil.cpu_freq(percpu=False) #리눅스에서 에러남

    class Memory:
        def __init__(self):
            self.virtual_memory = psutil.virtual_memory()
            self.swap_memory = psutil.swap_memory() # total이 used보다 작게 나와서 버그생김

    class Disk:
        def __init__(self):
            self.partitions = [x for x in psutil.disk_partitions(all=False)] #고정값
            self.disk_usage = [psutil.disk_usage(x.device) for x in self.partitions]
            self.disk_io_counters = psutil.disk_io_counters(perdisk=False, nowrap=True)

    class Network:
        def __init__(self):
            self.net_io_counters = psutil.net_io_counters(pernic=False, nowrap=True)
            #self.net_connections = psutil.net_connections(kind='inet')
            self.net_if_addrs = psutil.net_if_addrs() #고정값 네트워크 정보는 고정되어 있는 경우가 대부분이지만 바뀔 수도 있음
            self.net_if_stats = psutil.net_if_stats()

    class Sensor:
        def __init__(self):
            #self.sensors_temperatures = psutil.sensors_temperatures(fahrenheit=False) # 감지 안 됨
            #self.sensors_fans = psutil.sensors_fans() # 감지 안 됨
            self.sensors_battery = psutil.sensors_battery() #노트북에선 되는데 배터리 없을 경우 테스트 필요

    class TimeSpent:
        def __init__(self):
            self.boot_time = psutil.boot_time()

    class User:
        def __init__(self):
            self.users = psutil.users()

    class Process:
        def __init__(self):
            self.processes = [dict(x.info) for x in psutil.process_iter(['pid', 'name', 'username'])]
    
    class WinService:
        def __init__(self):
            self.services = [x for x in psutil.win_service_iter()] #리눅스에서 에러남

    def to_JSON(self):
        return json.dumps(self, default=lambda x: self.namedtuple_asdict(x.__dict__), ensure_ascii=False, indent=4)

    def namedtuple_asdict(self, obj):
        if hasattr(obj, "_asdict"): # detect namedtuple
            return OrderedDict(zip(obj._fields, (self.namedtuple_asdict(item) for item in obj)))
        elif isinstance(obj, str): # iterables - strings
            return obj
        elif hasattr(obj, "keys"): # iterables - mapping
            return OrderedDict(zip(obj.keys(), (self.namedtuple_asdict(item) for item in obj.values())))
        elif hasattr(obj, "__iter__"): # iterables - sequence
            return type(obj)((self.namedtuple_asdict(item) for item in obj))
        else: # non-iterable cannot contain namedtuples
            return obj