# Component Methods

## EventParser
- `SchedulingFailureInfo parse(String rawEventJson)` — 解析原始 JSON，提取资源请求、node 条件、taints、亲和性等信息
- `void validate(String rawEventJson)` — 验证 JSON 格式合法性，抛出异常如格式错误

## PromptBuilder
- `String buildPrompt(SchedulingFailureInfo info)` — 基于事件信息构造结构化 prompt

## BedrockClient
- `String invoke(String prompt)` — 调用 Bedrock Claude 4.6 Sonnet，返回原始响应文本

## ReportParser
- `AnalysisReport parse(String aiResponse)` — 将 AI 响应解析为结构化报告对象

## ReportFormatter
- `String formatAsJson(AnalysisReport report)` — 输出 JSON 格式
- `String formatAsText(AnalysisReport report)` — 输出人类可读纯文本格式

## AnalysisController
- `ResponseEntity<AnalysisReport> analyze(EventRequest request)` — REST 端点，编排完整分析流程

## CliRunner
- `void run(String[] args)` — CLI 入口，解析参数、执行分析、输出结果
