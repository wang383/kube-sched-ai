# Services

## AnalysisService（分析编排服务）
- **职责**: 编排完整的分析流程，协调各组件完成从事件解析到报告生成的全链路
- **编排流程**:
  1. EventParser.validate() → 验证输入
  2. EventParser.parse() → 提取事件信息
  3. PromptBuilder.buildPrompt() → 构造 prompt
  4. BedrockClient.invoke() → 调用 AI
  5. ReportParser.parse() → 解析 AI 响应
  6. 返回 AnalysisReport
- **调用方**: AnalysisController (HTTP)、CliRunner (CLI)
