docker run -d -p 9000:9000 --name minio1 \
  -e "MINIO_ACCESS_KEY=your_access_key" \
  -e "MINIO_SECRET_KEY=your_secret_key" \
  -v /home/${USER}/minio/data:/data \
  -v /home/${USER}/minio/config:/root/.minio \
  minio/minio server /data