# Unit Test Execution

## 运行全部单元测试

```bash
cd /Users/wangey/Desktop/pincap-aidlc/kube-sched-ai
mvn test
```

## 期望结果

| 测试类 | 测试数 | 说明 |
|--------|--------|------|
| EventParserTest | 5 | JSON 验证和解析 |
| PromptBuilderTest | 1 | Prompt 包含事件信息 |
| ReportParserTest | 3 | JSON 提取和解析 |
| ReportFormatterTest | 2 | 文本和 JSON 格式化 |
| AnalysisServiceTest | 2 | 全链路编排 (mock Bedrock) |

- **总计**: 13 个测试
- **期望**: 全部通过，0 失败
- **测试报告**: `kube-sched-ai-core/target/surefire-reports/`

## 单独运行某个测试类

```bash
mvn test -pl kube-sched-ai-core -Dtest=EventParserTest
```
