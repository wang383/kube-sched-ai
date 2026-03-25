# Application Design — 综合文档

## 技术决策

| 决策项 | 选择 |
|--------|------|
| 构建工具 | Maven |
| 代码组织 | 多仓库，前后端分离部署 |
| API 风格 | RESTful (`POST /api/v1/analysis`) |
| 前后端通信 | 前端直接调用后端 API (CORS) |
| CLI 实现 | 独立入口类（不启动 Web 服务器） |

## 组件架构

7 个核心组件，1 个编排服务：

| 组件 | 职责 |
|------|------|
| EventParser | 解析 K8s FailedScheduling event JSON |
| PromptBuilder | 构造 Bedrock prompt |
| BedrockClient | 调用 AWS Bedrock Claude 4.6 Sonnet |
| ReportParser | 解析 AI 响应为结构化报告 |
| ReportFormatter | 报告格式化（JSON / 纯文本） |
| AnalysisController | REST API 端点 |
| CliRunner | 独立 CLI 入口 |
| AnalysisService | 编排服务，协调全链路 |

## 数据流

```
[JSON Input] → EventParser → SchedulingFailureInfo → PromptBuilder → [Prompt]
    → BedrockClient → [AI Response] → ReportParser → AnalysisReport
    → ReportFormatter → [JSON or Text Output]
```

## 入口点

- **HTTP**: `POST /api/v1/analysis` → AnalysisController → AnalysisService
- **CLI**: `java -cp ... CliRunner <event.json>` → AnalysisService → stdout 纯文本

## 前端（独立仓库）

- Vue 3 + Element Plus
- 调用后端 `POST /api/v1/analysis`
- 展示结构化 AnalysisReport
