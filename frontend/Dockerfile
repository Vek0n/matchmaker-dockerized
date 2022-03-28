FROM node:13.12.0-alpine
WORKDIR /app/frontend
COPY package.json package-lock.json ./
RUN npm install 
RUN npm install react-scripts@3.4.1 -g 
RUN npm install react-router-dom@5.2.0
COPY . ./
EXPOSE 3000