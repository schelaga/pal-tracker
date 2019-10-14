package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
@RestController
public class EnvController {

    private String port;

    private String memoryLimit;

    private String cfInstanceIndex;

    private String cfInstanceAddress;

    public EnvController(@Value("${port:NOT SET}") String s1,
                         @Value("${memory.limit:NOT SET}") String s2,
                         @Value("${cf.instance.index:NOT SET}") String s3,
                         @Value("${cf.instance.addr:NOT SET}") String s4){
        port = s1;
        memoryLimit = s2;
        cfInstanceIndex = s3;
        cfInstanceAddress = s4;

    }
    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> env = new HashMap<>();

        env.put("PORT", port);
        env.put("MEMORY_LIMIT", memoryLimit);
        env.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        env.put("CF_INSTANCE_ADDR", cfInstanceAddress);

        return env;
    }
}
