# AI-DLC 工作流程图

## 完整流程图

```mermaid
flowchart TD
    START(["用户输入: Using AI-DLC..."])
    START --> WD

    subgraph INCEPTION["INCEPTION 立项阶段"]
        WD["工作区检测"]
        RE["逆向工程 - 仅Brownfield"]
        RA["需求分析"]
        US["用户故事 - 条件执行"]
        WP["工作流规划"]
        AD["应用设计 - 条件执行"]
        UG["工作单元生成 - 条件执行"]
    end

    subgraph CONSTRUCTION["CONSTRUCTION 构建阶段"]
        UNIT_LOOP["Per-Unit Loop"]
        FD["功能设计"]
        NFR_REQ["非功能需求"]
        NFR_DESIGN["非功能设计"]
        INFRA["基础设施设计"]
        CG["代码生成"]
        BT["构建与测试"]
    end

    subgraph OPERATIONS["OPERATIONS 运维阶段"]
        OPS["运维"]
    end

    APPROVE1{{"用户审批"}}
    APPROVE2{{"用户审批"}}
    APPROVE3{{"用户审批"}}
    APPROVE4{{"用户审批"}}
    APPROVE5{{"用户审批"}}

    WD --> |Brownfield| RE
    WD --> |Greenfield| RA
    RE --> APPROVE1
    APPROVE1 --> RA
    RA --> US
    US --> WP
    WP --> APPROVE2
    APPROVE2 --> AD
    AD --> UG
    UG --> UNIT_LOOP
    UNIT_LOOP --> FD
    FD --> NFR_REQ
    NFR_REQ --> NFR_DESIGN
    NFR_DESIGN --> INFRA
    INFRA --> CG
    CG --> APPROVE3
    APPROVE3 --> |"下一个单元"| UNIT_LOOP
    APPROVE3 --> |"所有单元完成"| BT
    BT --> APPROVE4
    APPROVE4 --> OPS
    OPS --> APPROVE5
    APPROVE5 --> END(["完成"])
```

---

## 关键决策点说明

### ⭐ 技术栈在哪个阶段选择？

技术栈选择分两步：

1. **需求分析阶段（Inception）** — 初步确定技术方向
   - 通过结构化问卷收集偏好
   - 评估现有技术栈约束（Brownfield）
   - 确定大方向（如：前端框架、后端语言、数据库类型）

2. **NFR需求阶段（Construction）** — 最终确认技术栈
   - 结合性能、安全、扩展性要求做最终决策
   - 明确具体版本和配置

---

## 自适应深度说明

AI-DLC 不是每次都走完所有阶段，而是根据复杂度自动调整：

| 场景 | 跳过的阶段 |
|------|-----------|
| 简单 Bug 修复 | 用户故事、应用设计、单元生成、NFR系列、基础设施设计 |
| 新功能开发 | 逆向工程（Greenfield）、部分NFR阶段 |
| 全新复杂系统 | 几乎不跳过任何阶段 |
| 已有系统改造 | 逆向工程必须执行 |

---

## 产出物目录结构

```
aidlc-docs/
├── inception/
│   ├── requirements/        # 需求文档
│   ├── user-stories/        # 用户故事
│   ├── application-design/  # 应用设计
│   └── reverse-engineering/ # 逆向工程文档（Brownfield）
├── construction/
│   ├── {unit-name}/
│   │   ├── functional-design/
│   │   ├── nfr-requirements/
│   │   ├── nfr-design/
│   │   ├── infrastructure-design/
│   │   └── code/
│   └── build-and-test/
├── aidlc-state.md           # 当前工作流状态
└── audit.md                 # 完整审计日志
```
