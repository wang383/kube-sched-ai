<template>
  <el-container style="min-height: 100vh">
    <el-header style="background: #409eff; color: #fff; display: flex; align-items: center">
      <h2 style="margin: 0">Kube Sched AI — Pod 调度失败分析</h2>
    </el-header>
    <el-main>
      <AnalysisView />
    </el-main>
  </el-container>
</template>

<script setup>
import AnalysisView from './views/AnalysisView.vue'
</script>
