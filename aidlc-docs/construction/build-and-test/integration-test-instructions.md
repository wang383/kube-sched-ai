# Integration Test Instructions

## 目的
验证前后端联调和 Bedrock API 集成。

## 场景 1: 后端 API 端到端测试

### 前置条件
- 后端服务已启动 (`java -jar kube-sched-ai-web/target/kube-sched-ai-web-1.0.0-SNAPSHOT.jar`)
- AWS 凭证已配置

### 测试步骤
```bash
# 使用 mock 数据调用 API
curl -X POST http://localhost:8080/api/v1/analysis \
  -H "Content-Type: application/json" \
  -d @kube-sched-ai-core/src/main/resources/mock-events/insufficient-resources.json \
  --data-urlencode "event@-" <<'EOF'
{"event": $(cat kube-sched-ai-core/src/main/resources/mock-events/insufficient-resources.json)}
EOF
```

简化版:
```bash
curl -X POST http://localhost:8080/api/v1/analysis \
  -H "Content-Type: application/json" \
  -d '{"event":{"reason":"FailedScheduling","message":"0/3 nodes are available: 1 Insufficient cpu","involvedObject":{"name":"test-pod","namespace":"default"},"firstTimestamp":"2026-03-25T05:30:00Z"}}'
```

### 期望结果
- HTTP 200
- 响应体包含 `category` 和 `description` 字段

## 场景 2: 前后端联调

### 前置条件
- 后端服务已启动 (localhost:8080)
- 前端开发服务已启动 (`npm run dev`, localhost:3000)

### 测试步骤
1. 浏览器打开 http://localhost:3000
2. 在 textarea 中粘贴 mock event JSON
3. 点击"分析"按钮
4. 验证报告展示区显示 category 和 description

## 场景 3: 错误处理验证

```bash
# 无效 JSON
curl -X POST http://localhost:8080/api/v1/analysis \
  -H "Content-Type: application/json" \
  -d '{"event":"not json"}'
# 期望: 400 Bad Request

# 缺少 event 字段
curl -X POST http://localhost:8080/api/v1/analysis \
  -H "Content-Type: application/json" \
  -d '{}'
# 期望: 400 Bad Request
```
