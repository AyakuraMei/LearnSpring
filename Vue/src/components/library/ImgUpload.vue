<template>
    <el-upload
    class="img-upload" ref="upload" action="http://localhost:8014/api/covers"
    :on-preview="handlePreview" :on-remove="handleRemove" :before-remove="beforeRemove" :on-success="handleSuccess"
    multiple :limit="1" :on-exceed="handleExceed" :file-list="fileList">
    <el-button size="small" type="primary">Upload</el-button>
    <div slot="tip" class="el-upload_tip">Only jpg / png Support.</div>
    </el-upload>
</template>

<script>
export default {
    name: 'ImgUpload',
    data() {
        return {
            fileList: [],
            url: ''
        }
    },
    methods: {
        handleRemove(file, fileList) {},
        handlePreview(file) {},
        handleExceed(files, fileList){
            this.$message.warning(`限制选择一个文件.`)
        },
        beforeRemove(file, fileList){
            return this.$confirm(`确定移除 ${file.name}`)
        },
        handleSuccess(response){
            this.url = response
            this.$emit('onUpload')
            this.$message.warning('上传成功')
        },
        clear(){
            this.$refs.upload.clearFiles()
        }
    }
}
</script>