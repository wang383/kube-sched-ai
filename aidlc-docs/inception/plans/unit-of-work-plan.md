# Unit of Work Plan

## 计划

- [x] 1. 定义工作单元及职责边界
- [x] 2. 生成工作单元依赖关系
- [x] 3. 生成需求到单元的映射
- [x] 4. 记录代码组织策略
- [x] 5. 验证单元边界和依赖

---

## 问题

请在每个 `[Answer]:` 标签后填写选项。完成后请告知我。

---

### 问题 1
后端项目中 CLI 入口和 Web 服务是否在同一个 Maven 模块中？

A) 单模块（CLI 和 Web 在同一个 Spring Boot 项目中，通过 profile 或参数区分）
B) 多模块（parent pom + backend-web 模块 + backend-cli 模块 + 共享 core 模块）

[Answer]: B

---
