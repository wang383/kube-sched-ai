# NFR Design Patterns — backend

## 1. Bedrock 调用 — 超时与错误处理模式

**模式**: Timeout + Exception Mapping

```
BedrockClient.invoke(prompt)
  ├── 设置 HTTP 超时 30s (AWS SDK apiCallTimeout)
  ├── 成功 → 返回响应文本
  ├── 超时 → 捕获 ApiCallTimeoutException → 抛出 BedrockTimeoutException
  └── 其他异常 → 捕获 SdkException → 抛出 BedrockInvocationException
```

**实现要点**:
- 使用 AWS SDK v2 的 `overrideConfiguration` 设置 `apiCallTimeout(Duration.ofSeconds(30))`
- 自定义异常类映射到 HTTP 状态码（503/502）

## 2. 全局异常处理模式

**模式**: Spring `@RestControllerAdvice`

| 异常 | HTTP 状态码 | 响应体 |
|------|------------|--------|
| IllegalArgumentException (输入验证) | 400 | `{"error": "..."}` |
| BedrockTimeoutException | 503 | `{"error": "AI service timeout"}` |
| BedrockInvocationException | 502 | `{"error": "AI service error"}` |
| ReportParseException | 502 | `{"error": "Failed to parse AI response"}` |

## 3. 日志模式

**模式**: 结构化日志 (SLF4J + Logback)

| 事件 | 级别 | 内容 |
|------|------|------|
| 收到分析请求 | INFO | podName, namespace |
| 事件解析完成 | INFO | 提取字段摘要 |
| Bedrock 调用开始 | INFO | prompt 长度 |
| Bedrock 调用完成 | INFO | 响应长度, 耗时 |
| Bedrock 调用失败 | ERROR | 异常信息 |
| 报告解析失败 | ERROR | 原始响应片段 |

## 4. CORS 配置模式

**模式**: Spring `@CrossOrigin` 或 `WebMvcConfigurer`

- 允许来源: `*`（MVP 阶段）
- 允许方法: `POST, GET, OPTIONS`
- 允许头: `Content-Type`
