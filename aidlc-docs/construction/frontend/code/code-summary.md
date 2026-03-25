# Code Summary — frontend

## 项目结构

```
kube-sched-ai-frontend/
├── index.html
├── package.json
├── vite.config.js
└── src/
    ├── main.js
    ├── App.vue
    ├── api/
    │   └── analysis.js
    └── views/
        └── AnalysisView.vue
```

## 技术栈
- Vue 3 + Element Plus
- Vite 6 构建
- Axios HTTP 客户端
- 开发代理: localhost:3000 → localhost:8080

## 页面功能
- 输入区: textarea 粘贴 FailedScheduling Event JSON
- 分析按钮: 调用 POST /api/v1/analysis
- 结果展示: El-Descriptions 展示 category + description
- 错误提示: El-Alert 展示错误信息
