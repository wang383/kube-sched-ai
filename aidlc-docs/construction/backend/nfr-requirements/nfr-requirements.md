# NFR Requirements — backend

## 性能需求

| 指标 | 要求 |
|------|------|
| Bedrock 调用超时 | 30 秒 |
| API 响应超时 | 35 秒（含 Bedrock 调用 + 处理开销） |

## 可靠性需求

| 场景 | 处理策略 |
|------|---------|
| Bedrock 调用超时 | 返回 503，日志记录 |
| Bedrock 返回非 JSON | 返回 502，日志记录 |
| 输入 JSON 非法 | 返回 400，错误信息 |

## 安全需求

- MVP 阶段无认证（本地运行）
- AWS 凭证通过环境变量或 ~/.aws/credentials 配置

## 可维护性需求

- SLF4J + Logback 日志
- 关键流程（Bedrock 调用、事件解析）记录 INFO 级别日志
- 错误场景记录 ERROR 级别日志
