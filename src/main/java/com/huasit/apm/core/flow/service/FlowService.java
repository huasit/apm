package com.huasit.apm.core.flow.service;

import com.huasit.apm.core.flow.entity.Flow;
import com.huasit.apm.core.flow.entity.FlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlowService {

    /**
     *
     */
    private Map<String, Map<String, Flow>> map = new HashMap<>();

    /**
     *
     */
    public synchronized Map<String, Flow> getTargetMap(String target) {
        Map<String, Flow> m = map.get(target);
        if (m != null) {
            return m;
        }
        m = new HashMap<>();
        List<Flow> flows = this.flowRepository.findByTarget(target);
        for (Flow flow : flows) {
            m.put(flow.getStage(), flow);
        }
        return m;
    }

    /**
     *
     */
    public Flow getStartNode(Class<?> c) {
        return this.getCurrentNode("[start]", c);
    }

    /**
     *
     */
    public Flow getCurrentNode(String stage, Class<?> c) {
        return this.getCurrentNode(c.getSimpleName().toLowerCase(), stage);
    }

    /**
     *
     */
    public Flow getCurrentNode(String target, String stage) {
        if ("apply".equals(stage)) {
            stage = "[start]";
        }
        Map<String, Flow> m = this.getTargetMap(target);
        return m.get(stage);
    }

    /**
     *
     */
    public Map<Integer, Flow> getTargetStatusMap(String target) {
        Map<Integer, Flow> map = new HashMap<>();
        List<Flow> list = this.flowRepository.findByTarget(target);
        for(Flow flow : list) {
            map.put(flow.getStatus(), flow);
        }
        return map;
    }

    /**
     *
     */
    @Autowired
    FlowRepository flowRepository;
}
