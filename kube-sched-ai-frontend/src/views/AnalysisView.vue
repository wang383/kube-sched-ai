<template>
  <div style="max-width: 800px; margin: 0 auto">
    <el-card shadow="never" style="margin-bottom: 20px">
      <template #header>输入 FailedScheduling Event JSON</template>
      <el-input
        v-model="eventJson"
        type="textarea"
        :rows="10"
        placeholder='粘贴 Kubernetes FailedScheduling Event JSON...'
        data-testid="event-json-input"
      />
      <el-button
        type="primary"
        style="margin-top: 12px"
        :loading="loading"
        data-testid="analyze-button"
        @click="handleAnalyze"
      >
        分析
      </el-button>
    </el-card>

    <el-alert
      v-if="error"
      :title="error"
      type="error"
      show-icon
      closable
      style="margin-bottom: 20px"
      data-testid="error-alert"
    />

    <el-card v-if="report" shadow="never" data-testid="report-card">
      <template #header>分析报告</template>
      <el-descriptions :column="1" border>
        <el-descriptions-item label="失败分类">
          <el-tag data-testid="report-category">{{ report.category }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="描述">
          <span data-testid="report-description">{{ report.description }}</span>
        </el-descriptions-item>
      </el-descriptions>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { analyzeEvent } from '../api/analysis'

const eventJson = ref('')
const report = ref(null)
const loading = ref(false)
const error = ref('')

async function handleAnalyze() {
  error.value = ''
  report.value = null
  if (!eventJson.value.trim()) {
    error.value = '请输入 Event JSON'
    return
  }
  loading.value = true
  try {
    const { data } = await analyzeEvent(eventJson.value)
    report.value = data
  } catch (e) {
    error.value = e.response?.data?.error || e.message
  } finally {
    loading.value = false
  }
}
</script>
