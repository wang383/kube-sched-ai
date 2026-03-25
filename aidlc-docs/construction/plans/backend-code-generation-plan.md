# Code Generation Plan — backend

## 单元上下文
- **单元**: backend (kube-sched-ai-backend)
- **类型**: Greenfield, Maven 多模块
- **代码位置**: 工作区根目录 `/Users/wangey/Desktop/pincap-aidlc/kube-sched-ai/`
- **模块**: core, web, cli
- **依赖**: FR-1~FR-5, FR-7

## 代码生成步骤

### Step 1: Maven 多模块项目结构
- [x] 创建 parent pom.xml (kube-sched-ai-backend)
- [x] 创建 kube-sched-ai-core/pom.xml
- [x] 创建 kube-sched-ai-web/pom.xml
- [x] 创建 kube-sched-ai-cli/pom.xml

### Step 2: Core 模块 — 数据模型
- [x] SchedulingFailureInfo.java (领域实体)
- [x] AnalysisReport.java (分析报告)
- [x] EventRequest.java (API 请求体)

### Step 3: Core 模块 — 自定义异常
- [x] BedrockTimeoutException.java
- [x] BedrockInvocationException.java
- [x] ReportParseException.java

### Step 4: Core 模块 — 业务组件
- [x] EventParser.java (事件解析)
- [x] PromptBuilder.java (Prompt 构造)
- [x] BedrockClient.java (Bedrock 调用)
- [x] ReportParser.java (报告解析)
- [x] ReportFormatter.java (报告格式化)

### Step 5: Core 模块 — 编排服务
- [x] AnalysisService.java

### Step 6: Core 模块 — Mock 数据
- [x] mock-events/ 目录下的 FailedScheduling JSON 样本文件

### Step 7: Web 模块 — API 层
- [x] AnalysisController.java (REST 端点)
- [x] GlobalExceptionHandler.java (全局异常处理)
- [x] CorsConfig.java (CORS 配置)
- [x] BedrockConfig.java (Bedrock 客户端 Bean 配置)
- [x] application.yml (Spring Boot 配置)
- [x] WebApplication.java (Spring Boot 启动类)

### Step 8: CLI 模块
- [x] CliRunner.java (独立 main 入口)

### Step 9: 单元测试
- [x] EventParserTest.java
- [x] PromptBuilderTest.java
- [x] ReportParserTest.java
- [x] ReportFormatterTest.java
- [x] AnalysisServiceTest.java (mock BedrockClient)

### Step 10: 文档
- [x] aidlc-docs/construction/backend/code/code-summary.md
