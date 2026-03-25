# Build Instructions

## Prerequisites
- **JDK**: OpenJDK 23
- **Maven**: 3.9+
- **Node.js**: 18+ (前端)
- **npm**: 9+ (前端)
- **AWS 凭证**: `~/.aws/credentials` 或环境变量 `AWS_ACCESS_KEY_ID` / `AWS_SECRET_ACCESS_KEY`

## 后端构建

### 1. 安装依赖并构建
```bash
cd /Users/wangey/Desktop/pincap-aidlc/kube-sched-ai
mvn clean install
```

### 2. 验证构建成功
- **期望输出**: `BUILD SUCCESS`
- **构建产物**:
  - `kube-sched-ai-core/target/kube-sched-ai-core-1.0.0-SNAPSHOT.jar`
  - `kube-sched-ai-web/target/kube-sched-ai-web-1.0.0-SNAPSHOT.jar` (可执行 Spring Boot JAR)
  - `kube-sched-ai-cli/target/kube-sched-ai-cli-1.0.0-SNAPSHOT.jar`

### 3. 启动 Web 服务
```bash
java -jar kube-sched-ai-web/target/kube-sched-ai-web-1.0.0-SNAPSHOT.jar
```
服务启动在 http://localhost:8080

### 4. CLI 使用
```bash
java -cp kube-sched-ai-cli/target/kube-sched-ai-cli-1.0.0-SNAPSHOT.jar:kube-sched-ai-core/target/kube-sched-ai-core-1.0.0-SNAPSHOT.jar com.kubesched.cli.CliRunner kube-sched-ai-core/src/main/resources/mock-events/insufficient-resources.json
```

## 前端构建

### 1. 安装依赖
```bash
cd kube-sched-ai-frontend
npm install
```

### 2. 开发模式运行
```bash
npm run dev
```
前端启动在 http://localhost:3000，自动代理 `/api` 到后端 8080

### 3. 生产构建
```bash
npm run build
```
产物在 `dist/` 目录

## Troubleshooting

### Maven 构建失败 — 依赖下载
- 检查网络连接和 Maven 仓库配置 (`~/.m2/settings.xml`)

### Bedrock 调用失败 — 凭证
- 确认 `~/.aws/credentials` 配置正确
- 确认 IAM 用户/角色有 `bedrock:InvokeModel` 权限
