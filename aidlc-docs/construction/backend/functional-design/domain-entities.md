# Domain Entities — backend

## SchedulingFailureInfo
事件解析后的结构化信息。

| 字段 | 类型 | 说明 |
|------|------|------|
| podName | String | Pod 名称 |
| namespace | String | 命名空间 |
| message | String | 调度失败消息（原始 reason message） |
| timestamp | Instant | 事件时间戳 |

## AnalysisReport
AI 分析结果的结构化报告。

| 字段 | 类型 | 说明 |
|------|------|------|
| category | String | 失败原因分类（AI 自由生成） |
| description | String | 失败原因描述 |

## EventRequest
REST API 请求体。

| 字段 | 类型 | 说明 |
|------|------|------|
| event | Object (raw JSON) | 原始 K8s FailedScheduling event JSON |
