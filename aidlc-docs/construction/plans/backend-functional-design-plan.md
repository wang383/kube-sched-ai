# Functional Design Plan — backend

## 计划

- [x] 1. 定义领域实体和数据模型
- [x] 2. 定义业务逻辑模型（事件解析、Prompt 构造、报告解析流程）
- [x] 3. 定义业务规则（失败原因分类规则、输入验证规则）
- [x] 4. 验证设计完整性

---

## 问题

请在每个 `[Answer]:` 标签后填写选项。完成后请告知我。

---

### 问题 1
FailedScheduling 事件解析后，SchedulingFailureInfo 需要提取哪些字段？

A) 基础字段：Pod 名称、命名空间、调度失败消息、时间戳
B) 基础 + 资源字段：A + CPU/Memory 请求量、节点选择器
C) 全面字段：B + Taints/Tolerations、亲和性/反亲和性规则、PVC 绑定状态
D) Other（请描述）

[Answer]: A

---

### 问题 2
Bedrock 返回的 AI 分析结果，期望的 JSON 结构是什么？

A) 简单结构：`{ "category": "...", "description": "..." }`
B) 标准结构：`{ "category": "...", "description": "...", "severity": "...", "suggestions": [...] }`
C) Other（请描述你期望的结构）

[Answer]: A

---

### 问题 3
失败原因分类的 category 枚举值，是由 AI 自由生成还是限定在预定义列表中？

A) 预定义列表（InsufficientCPU, InsufficientMemory, NodeSelectorMismatch, TaintNotTolerated, AffinityConflict, Other）
B) AI 自由生成，后端不做枚举约束

[Answer]: B

---
