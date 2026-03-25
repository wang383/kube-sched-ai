# 需求文档：Kubernetes Pod 调度失败智能分析工具 (kube-sched-ai)

## 意图分析摘要

- **用户请求**: 开发一个 Kubernetes Pod 调度失败智能分析工具，监听 FailedScheduling 事件，调用 AWS Bedrock 生成结构化分析报告
- **请求类型**: New Project（全新项目）
- **范围估计**: Multiple Components（后端服务 + CLI + 前端网页）
- **复杂度估计**: Moderate（多组件协作，涉及外部 AI 服务调用）

---

## 功能需求

### FR-1: FailedScheduling 事件解析
- 接收 mock 的 Kubernetes FailedScheduling event JSON
- 提取关键信息：资源请求（CPU/Memory）、node 条件、taints/tolerations、亲和性规则等
- 验证输入 JSON 格式合法性

### FR-2: AI 分析引擎
- 基于提取的事件信息构造 prompt
- 调用 AWS Bedrock Claude 4.6 Sonnet 模型
- 解析 AI 返回结果为结构化分析报告

### FR-3: 分析报告 — 失败原因分类
- 对调度失败原因进行分类：
  - 资源不足（Insufficient CPU/Memory）
  - 节点选择器不匹配（NodeSelector mismatch）
  - Taint 未容忍（Taint not tolerated）
  - 亲和性/反亲和性冲突（Affinity/Anti-affinity conflict）
  - 其他原因

### FR-4: 双格式输出
- CLI 输出：人类可读的纯文本格式
- API 输出：JSON 结构化格式，供前端网页渲染

### FR-5: 后端 REST API
- Spring Boot HTTP 服务，提供 REST API
- 接收 FailedScheduling event JSON 作为请求体
- 返回分析报告（JSON 格式）

### FR-6: 前端网页
- Vue + Element Plus 单页应用
- 展示结构化分析报告
- 调用后端 REST API 获取数据

### FR-7: Mock 数据
- 设计符合 Kubernetes 事件格式的 FailedScheduling mock 样本
- 覆盖主要失败场景（资源不足、taint、亲和性等）

---

## 非功能需求

### NFR-1: 技术栈
- **后端**: Java (OpenJDK 23) + Spring Boot
- **前端**: Vue 3 + Element Plus
- **AI 模型**: AWS Bedrock Claude 4.6 Sonnet
- **构建工具**: Maven/Gradle (后端), npm/pnpm (前端)

### NFR-2: MVP 范围
- 输入：mock 的 FailedScheduling event JSON（非实时 K8s 集群监听）
- 输出：CLI 纯文本 + 网页结构化报告
- 单用户本地运行

---

## 架构概览

```
[Mock JSON] → [Spring Boot API] → [Prompt 构造] → [AWS Bedrock] → [分析报告]
                    ↓                                                    ↓
              [CLI 纯文本输出]                                   [JSON API 响应]
                                                                       ↓
                                                            [Vue + Element Plus 前端]
```
