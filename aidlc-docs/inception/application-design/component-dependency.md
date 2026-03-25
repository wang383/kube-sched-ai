# Component Dependencies

## 依赖关系

```
AnalysisController ──→ AnalysisService
CliRunner ──→ AnalysisService ──→ ReportFormatter

AnalysisService ──→ EventParser
AnalysisService ──→ PromptBuilder
AnalysisService ──→ BedrockClient
AnalysisService ──→ ReportParser
```

## 依赖矩阵

| 组件 | 依赖 |
|------|------|
| AnalysisController | AnalysisService, ReportFormatter |
| CliRunner | AnalysisService, ReportFormatter |
| AnalysisService | EventParser, PromptBuilder, BedrockClient, ReportParser |
| EventParser | (无外部依赖) |
| PromptBuilder | (无外部依赖) |
| BedrockClient | AWS Bedrock SDK |
| ReportParser | (无外部依赖) |
| ReportFormatter | (无外部依赖) |

## 数据流

```
[JSON Input] → EventParser → SchedulingFailureInfo → PromptBuilder → [Prompt]
    → BedrockClient → [AI Response] → ReportParser → AnalysisReport
    → ReportFormatter → [JSON or Text Output]
```
