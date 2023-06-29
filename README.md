# Simple JPA Shop Project (CRUD)

## Domain

### Requirement
![](img/Screenshot%202023-06-29%20at%203.53.26%20PM.png)
Function List
- Member
	- Member registration
	- Member list
- Item
	- Item registration
	- Item update
	- Item list
- Order
	- Order registration
	- Order list
	- Order cancel
- ETC
	- item count
	- item type - Book, Album, Movie
	- item category
	- Putting delivery info when making order

## Domain model & Table
![](img/Screenshot%202023-06-29%20at%204.01.45%20PM.png)

### Member Entity
![](img/Screenshot%202023-06-29%20at%204.02.30%20PM.png)

### Member Table
![](img/Screenshot%202023-06-29%20at%204.03.07%20PM.png)

## Application Architecture
![](img/Screenshot%202023-06-29%20at%204.03.36%20PM.png)
- controller, web: web layer
- service: business logic, transaction
- repository: using JPA and EntityManager
- domain: group of entities, use in all layers

### Package Structure
- jpabook.jpashop
	- domain
	- exception
	- repository
	- service
	- web

## Web Layer Development

### 회원가입
![](img/Screenshot%202023-06-29%20at%204.06.40%20PM.png)

### 회원 목록
![](img/Screenshot%202023-06-29%20at%204.09.58%20PM.png)

### 상품 등록
![](img/Screenshot%202023-06-29%20at%204.10.18%20PM.png)

### 상품 목록
![](img/Screenshot%202023-06-29%20at%204.10.57%20PM.png)

### 상품 주문
![](img/Screenshot%202023-06-29%20at%204.11.32%20PM.png)

### 주문 내역
![](img/Screenshot%202023-06-29%20at%204.12.29%20PM.png)

## API
- 회원 등록 API
- 회원 수정 API
- 회원 조회 API