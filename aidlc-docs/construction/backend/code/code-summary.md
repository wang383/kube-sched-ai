# Code Summary — backend

## 项目结构

```
kube-sched-ai/
├── pom.xml                          (parent)
├── kube-sched-ai-core/
│   ├── pom.xml
│   └── src/main/java/com/kubesched/core/
│       ├── model/
│       │   ├── SchedulingFailureInfo.java
│       │   ├── AnalysisReport.java
│       │   └── EventRequest.java
│       ├── exception/
│       │   ├── BedrockTimeoutException.java
│       │   ├── BedrockInvocationException.java
│       │   └── ReportParseException.java
│       ├── component/
│       │   ├── EventParser.java
│       │   ├── PromptBuilder.java
│       │   ├── BedrockClient.java
│       │   ├── ReportParser.java
│       │   └── ReportFormatter.java
│       └── service/
│           └── AnalysisService.java
├── kube-sched-ai-web/
│   ├── pom.xml
│   └── src/main/java/com/kubesched/web/
│       ├── WebApplication.java
│       ├── config/
│       │   ├── BedrockConfig.java
│       │   └── CorsConfig.java
│       └── controller/
│           ├── AnalysisController.java
│           └── GlobalExceptionHandler.java
└── kube-sched-ai-cli/
    ├── pom.xml
    └── src/main/java/com/kubesched/cli/
        └── CliRunner.java
```

## 文件统计
- 数据模型: 3 个 record 类
- 自定义异常: 3 个
- 业务组件: 5 个
- 编排服务: 1 个
- Web 层: 4 个（Controller, ExceptionHandler, BedrockConfig, CorsConfig）
- CLI: 1 个
- 配置: 1 个 (application.yml)
- Mock 数据: 3 个 JSON 文件
- 单元测试: 5 个测试类
