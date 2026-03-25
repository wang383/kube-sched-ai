# 需求澄清问题

请通过在每个 `[Answer]:` 标签后填写字母选项来回答以下问题。
如果没有合适的选项，请选择最后一个选项（Other）并在标签后描述你的需求。
完成后请告知我。

---

## 问题 1
MVP 阶段调用的 AWS Bedrock 模型是哪个？

A) Claude 3 Haiku（速度快、成本低）
B) Claude 3 Sonnet（平衡速度与质量）
C) Claude 3 Opus（最高质量）
D) Other（请在 [Answer]: 后描述）

[Answer]: claude 4.6 Sonnet

---

## 问题 2
分析报告的输出格式偏好是什么？

A) 纯文本（人类可读，适合 CLI 直接展示）
B) JSON 结构化（方便程序处理和网页渲染）
C) 两者都要（CLI 输出纯文本，网页用 JSON）
D) Other（请在 [Answer]: 后描述）

[Answer]: C

---

## 问题 3
后端服务的运行方式是什么？

A) 本地命令行工具（直接运行 Python/Go 脚本）
B) 本地 HTTP 服务（FastAPI/Flask，CLI 和网页都调用它）
C) Other（请在 [Answer]: 后描述）

[Answer]: 后端服务采用openjdk 23开发，框架使用spring boot

---

## 问题 4
网页输出的复杂度要求是什么？

A) 极简：单页 HTML，直接展示分析结果文本
B) 简单：带基本样式的单页应用，展示结构化报告
C) Other（请在 [Answer]: 后描述）

[Answer]: 网页前端使用VUE ，ui框架使用Element Plus

---

## 问题 5
项目的编程语言偏好是什么？

A) Python（生态丰富，AWS SDK boto3 成熟）
B) Go（性能好，适合 CLI 工具）
C) TypeScript/Node.js
D) Other（请在 [Answer]: 后描述）

[Answer]: java 使用openjdk 23

---

## 问题 6
mock 的 FailedScheduling event JSON 是否已有样本，还是需要我们设计？

A) 已有样本，会在开发时提供
B) 需要你设计一个符合 Kubernetes 事件格式的 mock 样本
C) Other（请在 [Answer]: 后描述）

[Answer]: B

---

## 问题 7
分析报告需要包含哪些核心内容？（可多选，请列出字母）

A) 失败原因分类（资源不足 / 节点选择器不匹配 / Taint 未容忍 / 亲和性冲突等）
B) 人话版问题解释（非技术人员也能看懂）
C) 具体修复建议（kubectl 命令或配置修改示例）
D) 受影响的节点列表和状态摘要
E) Other（请在 [Answer]: 后描述）

[Answer]: A

---
