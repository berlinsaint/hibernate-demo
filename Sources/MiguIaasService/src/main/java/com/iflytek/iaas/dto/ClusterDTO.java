package com.iflytek.iaas.dto;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.iaas.domain.Cluster;
import com.iflytek.iaas.domain.Server;
import com.iflytek.iaas.domain.User;
import com.iflytek.iaas.dto.k8s.NetworkFlowDTO;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * 〈集群DTO〉
 *
 * @author ruizhao3
 * @create 2018/4/10
 */
public class ClusterDTO{

    private Integer id;
    private String name;
    private String annotation;
    private boolean valid;
    private Date createtime;
    private String labelName;
    private List<Server> servers;
    private JSONArray cpuUsage;
    private NetworkFlowDTO networkUsage;
    private JSONArray memoryUsage;
    private int podsNum;
    private User user;

    public Cluster toCluster() {
        Cluster cluster = new Cluster();
        BeanUtils.copyProperties(this, cluster);
        return cluster;
    }

    public int getPodsNum() {
        return podsNum;
    }

    public void setPodsNum(int podsNum) {
        this.podsNum = podsNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public JSONArray getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(JSONArray cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public NetworkFlowDTO getNetworkUsage() {
        return networkUsage;
    }

    public void setNetworkUsage(NetworkFlowDTO networkUsage) {
        this.networkUsage = networkUsage;
    }

    public JSONArray getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(JSONArray memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
