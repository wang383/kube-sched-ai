# Unit of Work Dependencies

## 依赖矩阵

| 单元 | 依赖 | 类型 |
|------|------|------|
| kube-sched-ai-web | kube-sched-ai-core | Maven 模块依赖 |
| kube-sched-ai-cli | kube-sched-ai-core | Maven 模块依赖 |
| kube-sched-ai-core | AWS Bedrock SDK | 外部依赖 |
| frontend | backend (web) | HTTP API 调用 |

## 构建顺序

1. **kube-sched-ai-core** — 先构建，无内部依赖
2. **kube-sched-ai-web** + **kube-sched-ai-cli** — 并行构建，均依赖 core
3. **frontend** — 独立构建，运行时依赖后端 API

## 集成点

- **backend ↔ frontend**: REST API `POST /api/v1/analysis`（JSON 请求/响应）
- **core ↔ AWS Bedrock**: AWS SDK for Java，调用 Claude 4.6 Sonnet
