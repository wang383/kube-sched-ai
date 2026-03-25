# Unit of Work — Requirements Mapping

## 需求到工作单元映射

| 需求 | 工作单元 |
|------|---------|
| FR-1: FailedScheduling 事件解析 | backend / core (EventParser) |
| FR-2: AI 分析引擎 | backend / core (PromptBuilder, BedrockClient) |
| FR-3: 失败原因分类 | backend / core (ReportParser) |
| FR-4: 双格式输出 | backend / core (ReportFormatter) |
| FR-5: 后端 REST API | backend / web (AnalysisController) |
| FR-6: 前端网页 | frontend |
| FR-7: Mock 数据 | backend / core (test resources) |
