# Logical Components — backend

## 组件清单

| 组件 | 类型 | 说明 |
|------|------|------|
| BedrockRuntimeClient | AWS SDK 客户端 | Spring Bean，配置超时和 region |
| GlobalExceptionHandler | @RestControllerAdvice | 统一异常到 HTTP 状态码映射 |
| CorsConfig | WebMvcConfigurer | CORS 跨域配置 |

## Spring Bean 配置

```
@Configuration
BedrockConfig
  └── BedrockRuntimeClient bean (region, timeout 配置)

@RestControllerAdvice
GlobalExceptionHandler
  ├── handleIllegalArgument → 400
  ├── handleBedrockTimeout → 503
  ├── handleBedrockInvocation → 502
  └── handleReportParse → 502

@Configuration
CorsConfig
  └── addCorsMappings("/api/**", allowAll)
```

## 自定义异常类

| 异常类 | 用途 |
|--------|------|
| BedrockTimeoutException | Bedrock 调用超时 |
| BedrockInvocationException | Bedrock 调用失败 |
| ReportParseException | AI 响应解析失败 |
