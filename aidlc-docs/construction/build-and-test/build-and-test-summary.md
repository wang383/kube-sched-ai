# Build and Test Summary

## Build Status
- **后端构建工具**: Maven 3.9+ / OpenJDK 23
- **前端构建工具**: Vite 6 / Node.js 18+
- **构建产物**:
  - `kube-sched-ai-web-1.0.0-SNAPSHOT.jar` (Spring Boot 可执行 JAR)
  - `kube-sched-ai-cli-1.0.0-SNAPSHOT.jar` (CLI JAR)
  - `kube-sched-ai-frontend/dist/` (前端静态文件)

## Test Execution Summary

### Unit Tests
- **总计**: 13 个测试 (5 个测试类)
- **覆盖**: EventParser, PromptBuilder, ReportParser, ReportFormatter, AnalysisService
- **BedrockClient**: 通过 Mockito mock 测试

### Integration Tests
- **场景 1**: 后端 API curl 端到端 (需 AWS 凭证)
- **场景 2**: 前后端联调 (浏览器手动测试)
- **场景 3**: 错误处理验证 (400 响应)

### Performance Tests
- N/A (MVP 阶段，单用户本地运行)

### Additional Tests
- **Contract Tests**: N/A (单后端服务)
- **Security Tests**: N/A (MVP 无认证)
- **E2E Tests**: N/A (手动集成测试覆盖)

## 运行顺序
1. `mvn clean install` — 构建后端 + 运行单元测试
2. `cd kube-sched-ai-frontend && npm install && npm run dev` — 启动前端
3. `java -jar kube-sched-ai-web/target/kube-sched-ai-web-1.0.0-SNAPSHOT.jar` — 启动后端
4. 执行集成测试场景
