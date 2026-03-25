# Unit of Work

## 工作单元定义

本项目拆分为 2 个独立仓库、3 个工作单元：

### Unit 1: backend（后端服务 — Maven 多模块）

**仓库**: kube-sched-ai-backend

**Maven 模块结构**:
```
kube-sched-ai-backend/          (parent pom)
├── kube-sched-ai-core/         (共享核心模块)
├── kube-sched-ai-web/          (Spring Boot Web 服务)
└── kube-sched-ai-cli/          (独立 CLI 入口)
```

**kube-sched-ai-core** (共享核心):
- EventParser — 事件解析
- PromptBuilder — Prompt 构造
- BedrockClient — Bedrock 调用
- ReportParser — 报告解析
- ReportFormatter — 报告格式化
- AnalysisService — 编排服务
- 数据模型（SchedulingFailureInfo, AnalysisReport）
- Mock 数据样本（JSON 资源文件）

**kube-sched-ai-web** (Web 服务):
- AnalysisController — REST API 端点
- CORS 配置
- 依赖 core 模块

**kube-sched-ai-cli** (CLI 工具):
- CliRunner — 独立 main 入口
- 依赖 core 模块，不启动 Web 服务器

### Unit 2: frontend（前端应用）

**仓库**: kube-sched-ai-frontend

```
kube-sched-ai-frontend/
├── src/
│   ├── App.vue
│   ├── views/
│   ├── components/
│   └── api/
├── package.json
└── vite.config.js
```

- Vue 3 + Element Plus
- 调用后端 REST API `POST /api/v1/analysis`
- 展示结构化分析报告

## 代码组织策略

- **多仓库**: 前后端各自独立 Git 仓库
- **后端多模块**: Maven parent pom 管理 core/web/cli 三个子模块
- **共享逻辑**: 所有业务逻辑在 core 模块，web 和 cli 仅为入口层
