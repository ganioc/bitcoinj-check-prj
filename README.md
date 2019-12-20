# Introduction
## API for chain

DemoAddr     - Address, key, creation

DemoStatus   - get chain height, block, transaction

DemoTransfer - DemoTransfer.transferTo()

## Questions

```

1. 交易签名出来的字节流，反序列化成一个json结构，里面有amount、fromaddr这些信息供我们校验。提供js的sdk就行
如果交易签名出来的结构就是json的，有金额、fromaddr、toaddr这些字段，倒是不需要提供反序列化的sdk

A: 见Digest.parse()可以解析 byt[]为json, 如下所示

{"input":"{\"to\":\"s154bdF5WH3FXGo4v24F4dYwXnR8br8rc2r\"}","amount":"100","method":"transferTo","signature":"8d9468783b1673dab4ca29027c21cae9703969042c1b690236b7ebc14d9b00ba48d154a4895d247a6192d5efad22be8199c98d0d52fee4685363673aa241d346","fee":"0.1","publicKey":"02c7946884918dcc3f234e60aefa6c3269169e5a27d33824c8d1e10e2b7746e89d","nonce":13}



2. 构造交易必须要java demo，js的不行
A: DemoTransfer.java文件

3. 地址校验也得java sdk，js的不行
A: DemoAddr.java文件


```



```
bblock hash
018d334904fb550f8647feb63d80b7cb66dd28a90ffb3318fc970bfd83636972

tx hash
76f18f540323e7cf78619cfb41bb38687d06a12dec0b034fa0742ced93217e5f


Block  1497:  1
Block  1498:  1
4ed9a2c9b464da82eaa1afcb4bde8f4a07af27a9f31c8ac70d195075344022dc

Block  1499:  1
Block  1506:  1
Block  1507:  1
Block  1508:  1
Block  1509:  1
Block  1511:  1
Block  1512:  1
Block  1513:  1
Block  1514:  1
Block  1515:  1
Block  1516:  1
Block  1517:  1
Block  1518:  1
Block  1520:  1


```