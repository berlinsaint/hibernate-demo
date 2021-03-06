<template>
  <div class="content-container">
    <el-card class="cluster-info-card">
      <el-row class="cluster-row">
        <el-col :xs="12" :sm="12" :md="6" :lg="3" class="cluster-title">集群名称：</el-col>
        <el-col :xs="12" :sm="12" :md="18" :lg="21">{{cluster.name}}</el-col>
        <el-col :xs="12" :sm="12" :md="6" :lg="3" class="cluster-title">标签名：</el-col>
        <el-col :xs="12" :sm="12" :md="18" :lg="21">{{cluster.labelName}}</el-col>
        <el-col :xs="12" :sm="12" :md="6" :lg="3" class="cluster-title">备注： </el-col>
        <el-col :xs="12" :sm="12" :md="18" :lg="21">{{cluster.annotation}}</el-col>
        <el-col :xs="12" :sm="12" :md="6" :lg="3" class="cluster-title">创建人： </el-col>
        <el-col :xs="12" :sm="12" :md="18" :lg="21">{{cluster.user && cluster.user.nickname}}</el-col>
        <el-col :xs="12" :sm="12" :md="6" :lg="3" class="cluster-title">创建时间： </el-col>
        <el-col :xs="12" :sm="12" :md="18" :lg="21">{{new Date(cluster.createtime).toLocaleString()}}</el-col>
      </el-row>
    </el-card>

    <el-card class="grap-card">
      <div slot="header">cpu平均使用率：</div>
      <v-chart v-if="cpuUsage" :forceFit="true" :height="height" :data="cpuUsage" :scale="scale">
        <v-tooltip />
        <v-axis/>
        <v-line position="time*value" />
        <v-point position="time*value" shape="circle" />
      </v-chart>
      <div v-else>没有CPU使用率数据，因为没有向该集群添加主机</div>
    </el-card>

    <el-card class="grap-card">
      <div slot="header">内存平均使用率：</div>
      <v-chart v-if="memoryUsage" :forceFit="true" :height="height" :data="memoryUsage" :scale="scale">
        <v-tooltip />
        <v-axis />
        <v-line position="time*value" />
        <v-point position="time*value" shape="circle" />
      </v-chart>
      <div v-else>没有内存使用率数据，因为没有向该集群添加主机</div>
    </el-card>

    <el-card>
      <div slot="header">网络使用情况{{network}}</div>
      <v-chart v-if="network" :force-fit="true" :height="height" :data="network">
        <v-tooltip />
        <v-axis data-key="time" :tick-line="null" :label="null"/>
        <v-axis data-key="count" :label="countOpts.label"/>
        <v-legend />
        <v-line position="time*count" color="network" />
        <v-point position="time*count" color="network" :size="4"  :shape="'circle'" />
      </v-chart>
      <div v-else>没有网络使用数据，因为没有向该集群添加主机!</div>
    </el-card>

  </div>
</template>

<script>
import ClusterApi from '@/api/cluster';
import DataSet from '@antv/data-set';

function formatTime(time) {
  const t = new Date(time * 1000);
  return t.toLocaleString();
}

function formatUsage(usage) {
  return usage[0] && usage[0].values.map(i => ({ time: formatTime(i[0]), value: parseFloat(i[1]) }));
}

function byteToMb(b) {
  return parseFloat((b / 1024 / 1024).toFixed(2));
}
export default {
  data() {
    return {
      countOpts: {
        label: {
          formatter: val => `${(val)}MB/s`,
        },
      },
      cluster: {},
      cpuUsage: null,
      memoryUsage: null,
      network: null,
      scale: [{
        dataKey: 'value',
        formatter: '.2%',
        alias: '使用率',
        min: 0,
        max: 1,
      }, {
        dataKey: 'time',
      }],
      height: 400,
    };
  },
  created() {
    ClusterApi.show(this.$router.currentRoute.params.id).then((resp) => {
      this.cluster = resp;

      this.cpuUsage = formatUsage(this.cluster.cpuUsage);
      this.memoryUsage = formatUsage(this.cluster.memoryUsage);

      const transmits = this.cluster.networkUsage.transmitResult[0] && this.cluster.networkUsage.transmitResult[0].values;
      const receives = this.cluster.networkUsage.receiveResult[0] && this.cluster.networkUsage.receiveResult[0].values;
      if (transmits) {
        this.network = transmits.map((i) => {
          const receive = receives.find(j => i[0] === j[0]);
          const transmitValue = parseFloat(i[1]);
          const receiveValue = parseFloat(receive[1]);
          const totalValue = transmitValue + receiveValue;
          const a = {
            time: formatTime(i[0]),
            上行速率: byteToMb(transmitValue),
            下行速率: byteToMb(receiveValue),
            总速率: byteToMb(totalValue),
          };
          return a;
        });

        const dv = new DataSet.View().source(this.network);
        dv.transform({
          type: 'fold',
          fields: ['上行速率', '下行速率', '总速率'],
          key: 'network',
          value: 'count',
        });
        this.network = dv.rows;
      }
    });
  },
  method: {
  },
};
</script>

<style>
.content-container {
  margin: 28px;
}

  .grap-card {
    margin-bottom: 20px;
  }

  .cluster-info-card .el-col {
    border-bottom: lightgray 1px solid;
    padding: 10px;
  }

  .cluster-row .el-col {
    height: 39px;
  }
</style>
