# Components

## 1. EventParser（事件解析组件）
- **职责**: 接收并解析 Kubernetes FailedScheduling event JSON，提取调度相关关键信息
- **接口**:
  - 输入: 原始 K8s Event JSON
  - 输出: 结构化的 SchedulingFailureInfo 对象

## 2. PromptBuilder（Prompt 构造组件）
- **职责**: 基于解析后的事件信息构造 AWS Bedrock 调用的 prompt
- **接口**:
  - 输入: SchedulingFailureInfo
  - 输出: 格式化的 prompt 字符串

## 3. BedrockClient（Bedrock 调用组件）
- **职责**: 调用 AWS Bedrock Claude 4.6 Sonnet，获取 AI 分析结果
- **接口**:
  - 输入: prompt 字符串
  - 输出: AI 原始响应文本

## 4. ReportParser（报告解析组件）
- **职责**: 将 AI 原始响应解析为结构化分析报告
- **接口**:
  - 输入: AI 原始响应文本
  - 输出: AnalysisReport 对象

## 5. ReportFormatter（报告格式化组件）
- **职责**: 将分析报告转换为不同输出格式（JSON / 纯文本）
- **接口**:
  - 输入: AnalysisReport + 输出格式类型
  - 输出: 格式化的报告字符串

## 6. AnalysisController（REST API 控制器）
- **职责**: 暴露 REST API 端点，处理 HTTP 请求/响应
- **接口**:
  - `POST /api/v1/analysis` — 接收事件 JSON，返回 JSON 分析报告

## 7. CliRunner（CLI 入口）
- **职责**: 独立的命令行入口，读取 JSON 文件/stdin，输出纯文本报告
- **接口**:
  - 输入: JSON 文件路径或 stdin
  - 输出: 纯文本报告到 stdout
