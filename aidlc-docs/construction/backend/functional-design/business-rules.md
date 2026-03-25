# Business Rules — backend

## 输入验证规则

| 规则 | 条件 | 结果 |
|------|------|------|
| BR-1 | JSON 格式不合法 | 400 Bad Request |
| BR-2 | 缺少 reason 字段 | 400 Bad Request |
| BR-3 | reason 不是 "FailedScheduling" | 400 Bad Request |
| BR-4 | 缺少 message 字段 | 400 Bad Request |

## 失败原因分类规则

- category 由 AI 自由生成，后端不做枚举约束
- 后端仅负责将 AI 返回的 category 和 description 透传给调用方

## 报告格式化规则

- **JSON 格式** (API): 直接序列化 AnalysisReport 对象
- **纯文本格式** (CLI): `[Category] description` 格式输出

## 错误处理规则

| 场景 | 处理 |
|------|------|
| Bedrock 调用失败 | 返回 503 Service Unavailable |
| AI 响应无法解析为 JSON | 返回 502 Bad Gateway |
| 输入验证失败 | 返回 400 Bad Request + 错误信息 |
