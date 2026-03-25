# Business Logic Model — backend

## 分析流程

```
EventRequest → validate → parse → buildPrompt → invoke Bedrock → parse AI response → AnalysisReport
```

### 1. 事件验证 (EventParser.validate)
- 输入: 原始 JSON 字符串
- 验证 JSON 格式合法
- 验证包含必要的 K8s Event 字段（kind, reason, message）
- 验证 reason 为 "FailedScheduling"
- 失败: 抛出 IllegalArgumentException

### 2. 事件解析 (EventParser.parse)
- 输入: 原始 JSON 字符串
- 提取: podName, namespace, message, timestamp
- 输出: SchedulingFailureInfo

### 3. Prompt 构造 (PromptBuilder.buildPrompt)
- 输入: SchedulingFailureInfo
- 构造包含事件信息的 prompt，要求 AI 返回 JSON 格式 `{"category":"...","description":"..."}`
- 输出: prompt 字符串

### 4. Bedrock 调用 (BedrockClient.invoke)
- 输入: prompt 字符串
- 调用 AWS Bedrock Claude 4.6 Sonnet
- 输出: AI 原始响应文本

### 5. 报告解析 (ReportParser.parse)
- 输入: AI 原始响应文本
- 从响应中提取 JSON 部分
- 解析为 AnalysisReport 对象
- 输出: AnalysisReport
