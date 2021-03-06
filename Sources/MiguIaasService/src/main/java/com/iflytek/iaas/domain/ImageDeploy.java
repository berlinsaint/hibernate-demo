package com.iflytek.iaas.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "image_deploy")
@DynamicInsert
@DynamicUpdate
public class ImageDeploy {
    private Integer id;
    private String name;
    private Integer imageId;
    private Integer appId;
    private Integer clusterId;
    private String deployLabel;
    private Integer minPods;
    private Integer maxPods;
    private Integer pods;
    private Integer podContainers;
    private Integer containerPort;
    private Integer cpuLimits;
    private Integer memoryLimits;
    private Integer simultUpdates;
    private Integer timeOut;
    private boolean uniqueDeploy;
    private String healthCheck;
    private String envs;
    private String initCmd;
    private String mountDirs;
    private String deployType;
    private Byte deployStatus;
    private boolean autoDispatch;
    private boolean valid;
    private Date createtime;
    private Date updatetime;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "image_id")
    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @Basic
    @Column(name = "app_id")
    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "cluster_id")
    public Integer getClusterId() {
        return clusterId;
    }

    public void setClusterId(Integer clusterId) {
        this.clusterId = clusterId;
    }

    @Basic
    @Column(name = "deploy_label")
    public String getDeployLabel() {
        return deployLabel;
    }

    public void setDeployLabel(String deployLabel) {
        this.deployLabel = deployLabel;
    }

    @Basic
    @Column(name = "min_pods")
    public Integer getMinPods() {
        return minPods;
    }

    public void setMinPods(Integer minPods) {
        this.minPods = minPods;
    }

    @Basic
    @Column(name = "max_pods")
    public Integer getMaxPods() {
        return maxPods;
    }

    public void setMaxPods(Integer maxPods) {
        this.maxPods = maxPods;
    }

    @Basic
    @Column(name = "pods")
    public Integer getPods() {
        return pods;
    }

    public void setPods(Integer pods) {
        this.pods = pods;
    }

    @Basic
    @Column(name = "pod_containers")
    public Integer getPodContainers() {
        return podContainers;
    }

    public void setPodContainers(Integer podContainers) {
        this.podContainers = podContainers;
    }

    @Basic
    @Column(name = "container_port")
    public Integer getContainerPort() {
        return containerPort;
    }

    public void setContainerPort(Integer containerPort) {
        this.containerPort = containerPort;
    }

    @Basic
    @Column(name = "cpu_limits")
    public Integer getCpuLimits() {
        return cpuLimits;
    }

    public void setCpuLimits(Integer cpuLimits) {
        this.cpuLimits = cpuLimits;
    }

    @Basic
    @Column(name = "memory_limits")
    public Integer getMemoryLimits() {
        return memoryLimits;
    }

    public void setMemoryLimits(Integer memoryLimits) {
        this.memoryLimits = memoryLimits;
    }

    @Basic
    @Column(name = "simult_updates")
    public Integer getSimultUpdates() {
        return simultUpdates;
    }

    public void setSimultUpdates(Integer simultUpdates) {
        this.simultUpdates = simultUpdates;
    }

    @Basic
    @Column(name = "time_out")
    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    @Basic
    @Column(name = "unique_deploy")
    public boolean getUniqueDeploy() {
        return uniqueDeploy;
    }

    public void setUniqueDeploy(boolean uniqueDeploy) {
        this.uniqueDeploy = uniqueDeploy;
    }

    @Basic
    @Column(name = "health_check")
    public String getHealthCheck() {
        return healthCheck;
    }

    public void setHealthCheck(String healthCheck) {
        this.healthCheck = healthCheck;
    }

    @Basic
    @Column(name = "envs")
    public String getEnvs() {
        return envs;
    }

    public void setEnvs(String envs) {
        this.envs = envs;
    }

    @Basic
    @Column(name = "init_cmd")
    public String getInitCmd() {
        return initCmd;
    }

    public void setInitCmd(String initCmd) {
        this.initCmd = initCmd;
    }

    @Basic
    @Column(name = "mount_dirs")
    public String getMountDirs() {
        return mountDirs;
    }

    public void setMountDirs(String mountDirs) {
        this.mountDirs = mountDirs;
    }

    @Basic
    @Column(name = "deploy_type")
    public String getDeployType() {
        return deployType;
    }

    public void setDeployType(String deployType) {
        this.deployType = deployType;
    }

    @Basic
    @Column(name = "deploy_status")
    public Byte getDeployStatus() {
        return deployStatus;
    }

    public void setDeployStatus(Byte deployStatus) {
        this.deployStatus = deployStatus;
    }

    @Basic
    @Column(name = "auto_dispatch")
    public boolean getAutoDispatch() {
        return autoDispatch;
    }

    public void setAutoDispatch(boolean autoDispatch) {
        this.autoDispatch = autoDispatch;
    }

    @Basic
    @Column(name = "valid")
    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Basic
    @Column(name = "createtime")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "updatetime")
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDeploy that = (ImageDeploy) o;
        return id.equals(that.id) &&
                imageId.equals(that.imageId) &&
                appId.equals(that.appId) &&
                clusterId.equals(that.clusterId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(deployLabel, that.deployLabel) &&
                Objects.equals(minPods, that.minPods) &&
                Objects.equals(maxPods, that.maxPods) &&
                Objects.equals(pods, that.pods) &&
                Objects.equals(podContainers, that.podContainers) &&
                Objects.equals(containerPort, that.containerPort) &&
                Objects.equals(cpuLimits, that.cpuLimits) &&
                Objects.equals(memoryLimits, that.memoryLimits) &&
                Objects.equals(simultUpdates, that.simultUpdates) &&
                Objects.equals(timeOut, that.timeOut) &&
                Objects.equals(uniqueDeploy, that.uniqueDeploy) &&
                Objects.equals(healthCheck, that.healthCheck) &&
                Objects.equals(envs, that.envs) &&
                Objects.equals(initCmd, that.initCmd) &&
                Objects.equals(mountDirs, that.mountDirs) &&
                Objects.equals(deployType, that.deployType) &&
                Objects.equals(deployStatus, that.deployStatus) &&
                Objects.equals(autoDispatch, that.autoDispatch) &&
                Objects.equals(valid, that.valid) &&
                Objects.equals(createtime, that.createtime) &&
                Objects.equals(updatetime, that.updatetime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, imageId, appId, clusterId, deployLabel, minPods, maxPods, pods, podContainers, containerPort, cpuLimits, memoryLimits, simultUpdates, timeOut, uniqueDeploy, healthCheck, envs, initCmd, mountDirs, deployType, deployStatus, autoDispatch, valid, createtime, updatetime);
    }
}
