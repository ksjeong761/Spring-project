package com.devistat.server.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@AutoConfigureMockMvc
@SpringBootTest
public class DeviceStatusControllerTest {
    private final Logger logger = LoggerFactory.getLogger(DeviceStatusControllerTest.class);
	
	@Autowired
	MockMvc mockMvc;
	
	private void addDeviceStatus() throws Exception{
		String uri = "/devices/statuses";
		String content = "{\r\n"
				+ "   \"cpu\":{\r\n"
				+ "      \"cpu_times\":{\r\n"
				+ "         \"user\":45403.5625,\r\n"
				+ "         \"system\":26145.5625,\r\n"
				+ "         \"idle\":3259386.6875,\r\n"
				+ "         \"interrupt\":730.40625,\r\n"
				+ "         \"dpc\":591.40625\r\n"
				+ "      },\r\n"
				+ "      \"cpu_stats\":{\r\n"
				+ "         \"ctx_switches\":1421314380,\r\n"
				+ "         \"interrupts\":1043548788,\r\n"
				+ "         \"soft_interrupts\":0,\r\n"
				+ "         \"syscalls\":214991203\r\n"
				+ "      },\r\n"
				+ "      \"cpu_loadavg\":{\r\n"
				+ "         \"minute_1\":0.0,\r\n"
				+ "         \"minute_5\":0.0,\r\n"
				+ "         \"minute_15\":0.0\r\n"
				+ "      },\r\n"
				+ "      \"cpu_percent\":4.7,\r\n"
				+ "      \"cpu_times_percent\":{\r\n"
				+ "         \"user\":2.9,\r\n"
				+ "         \"system\":1.7,\r\n"
				+ "         \"idle\":95.3,\r\n"
				+ "         \"interrupt\":0.0,\r\n"
				+ "         \"dpc\":0.0\r\n"
				+ "      },\r\n"
				+ "      \"cpu_count\":12\r\n"
				+ "   },\r\n"
				+ "   \"memory\":{\r\n"
				+ "      \"virtual_memory\":{\r\n"
				+ "         \"total\":16486559744,\r\n"
				+ "         \"available\":6666706944,\r\n"
				+ "         \"percent\":59.6,\r\n"
				+ "         \"used\":9819852800,\r\n"
				+ "         \"free\":6666706944\r\n"
				+ "      },\r\n"
				+ "      \"swap_memory\":{\r\n"
				+ "         \"total\":2958086144,\r\n"
				+ "         \"used\":6162374656,\r\n"
				+ "         \"free\":-3204288512,\r\n"
				+ "         \"percent\":208.3,\r\n"
				+ "         \"sin\":0,\r\n"
				+ "         \"sout\":0\r\n"
				+ "      }\r\n"
				+ "   },\r\n"
				+ "   \"disk\":{\r\n"
				+ "      \"disk_io_counters\":{\r\n"
				+ "         \"read_count\":11358388,\r\n"
				+ "         \"write_count\":19455789,\r\n"
				+ "         \"read_bytes\":77547750912,\r\n"
				+ "         \"write_bytes\":134771268608,\r\n"
				+ "         \"read_time\":2930,\r\n"
				+ "         \"write_time\":1625\r\n"
				+ "      }\r\n"
				+ "   },\r\n"
				+ "   \"network\":{\r\n"
				+ "      \"net_io_counters\":{\r\n"
				+ "         \"bytes_sent\":21932233,\r\n"
				+ "         \"bytes_recv\":281717279,\r\n"
				+ "         \"packets_sent\":132923,\r\n"
				+ "         \"packets_recv\":302018,\r\n"
				+ "         \"errin\":0,\r\n"
				+ "         \"errout\":0,\r\n"
				+ "         \"dropin\":0,\r\n"
				+ "         \"dropout\":0\r\n"
				+ "      }\r\n"
				+ "   },\r\n"
				+ "   \"time\":{\r\n"
				+ "      \"logged_time\":1644844015,\r\n"
				+ "      \"boot_time\":\"2022-02-11 16:59:43\"\r\n"
				+ "   }\r\n"
				+ "}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
			.content(content)
			.contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void test() throws Exception{
//		addDeviceStatus();

		mockMvc.perform(MockMvcRequestBuilders.post("/devices"));
		mockMvc.perform(MockMvcRequestBuilders.post("/users"));
		
		String uri = "/devices/statuses";
		MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
	    info.add("start", "1144844967");
	    info.add("end", "2644845567");

		mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.params(info)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(result -> {
					MockHttpServletResponse response = result.getResponse();
					logger.info(response.getContentAsString());
				});
	}
}
