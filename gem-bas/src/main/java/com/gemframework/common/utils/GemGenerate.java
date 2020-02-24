package com.gemframework.common.utils;

import com.gemframework.common.constant.GemConstant;
import com.gemframework.common.enums.CodeType;
import com.gemframework.common.exception.GemException;
import com.gemframework.model.vo.database.Column;
import com.gemframework.model.vo.database.Tables;
import com.google.common.io.ByteStreams;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.gemframework.common.enums.CodeType.HTML;
import static com.gemframework.common.enums.CodeType.JAVA;

@Slf4j
@Component
public class GemGenerate {

    /**
     * @Title:  lowercaseFirst
     * @MethodName:  lowercaseFirst
     * @Param: [str]
     * @Retrun: java.lang.String
     * @Description: 首字母小写
     * @Date: 2019/11/29 23:22
     */
    public static String lowercaseFirst(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);

    }

    /**
     * @Title:  uppercaseFirst
     * @MethodName:  uppercaseFirst
     * @Param: [str]
     * @Retrun: java.lang.String
     * @Description: 首字母大写
     * @Date: 2019/11/29 23:22
     */
    public static String uppercaseFirst(String str) {
        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char)(chars[0] - 32);
        }
        return new String(chars);

    }

    /**
     * @Title:  createFile
     * @MethodName:  createFile
     * @Param: [fileName]
     * @Retrun: boolean
     * @Description: 创建文件
     * @Date: 2019/11/29 23:23
     */
    public static boolean createFile(File fileName)throws Exception{
        try{
            if(!fileName.exists()){
                fileName.createNewFile();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }


    /**
     * @Title:  readTxt
     * @MethodName:  readTxt
     * @Param: [file]
     * @Retrun: java.lang.String
     * @Description: 读取txt
     * @Date: 2019/11/29 23:22
     */
    public static String readTxt(File file) throws IOException {
        String s = "";
        InputStreamReader in = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader br = new BufferedReader(in);
        StringBuffer content = new StringBuffer();
        while ((s = br.readLine()) != null) {
            content.append(s);
            content.append("\r\n");
        }
        return content.toString();
    }


    /**
     * @Title:  writeTxtFile
     * @MethodName:  writeTxtFile
     * @Param: [content, fileName]
     * @Retrun: boolean
     * @Description: 写入TXT，覆盖原内容
     * @Date: 2019/11/29 23:23
     */
    public static boolean writeTxtFile(CodeType type,String content,TempParas tempParas,File fileName)throws Exception{

        String packageName = tempParas.getPackageName();
        String lowerEntity = tempParas.getEntityEn();
        String upperEntity = uppercaseFirst(lowerEntity);
        String lowerPkName = tempParas.getPkName();
        String upperPkName = uppercaseFirst(lowerPkName);
        Integer pageW = tempParas.getPageWidth();
        Integer pageH = tempParas.getPageHeight();
        String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String author = tempParas.getAuthor();

        if(type == JAVA){
            content = content
                    .replace("${package}",packageName)
                    .replace("${Entity}",upperEntity)
                    .replace("${entity}",lowerEntity)
                    .replace("${PkName}",upperPkName)
                    .replace("${pkName}",lowerPkName)
                    .replace("${datetime}",datetime)
                    .replace("${author}",author);

        }else if(type == HTML){

            String addFeildHtml = "";
            String editFeildHtml = "";
            String listFeildHtml = "";
            List<TempParas.Filed> fileds = tempParas.getFileds();
            if(fileds!=null&&fileds.size()>0){
                for(int i=0;i<fileds.size();i++){
                    TempParas.Filed filed = fileds.get(i);
                    if(!filed.getAttrName().equalsIgnoreCase("id")){
                        //添加页面：
                        addFeildHtml += "<tr>\n" +
                                "   <td>"+filed.getComment()+"";
                        if(filed.getIsNull() == 0){
                            addFeildHtml += "\n<span class=\"span\">*</span>";
                        }
                        addFeildHtml += "   </td>\n";
                        if(filed.getAttrType().equalsIgnoreCase("text")
                                || filed.getAttrType().equalsIgnoreCase("email")
                                || filed.getAttrType().equalsIgnoreCase("number")
                                || filed.getAttrType().equalsIgnoreCase("tel")
                                || filed.getAttrType().equalsIgnoreCase("checkbox")
                                || filed.getAttrType().equalsIgnoreCase("radio")
                                || filed.getAttrType().equalsIgnoreCase("date")
                                || filed.getAttrType().equalsIgnoreCase("hidden")
                                || filed.getAttrType().equalsIgnoreCase("password")
                        ){
                            addFeildHtml +="       <input type=\""+filed.getAttrType()+"\" " +
                                    "       id=\""+GemBeanUtils.lineToHump(filed.getAttrName())+"\" " +
                                    "       name=\""+GemBeanUtils.lineToHump(filed.getAttrName())+"\"" +
                                    //TODO: 设置布局
                                    "       class=\"block\" ";
                            if(filed.getIsNull() == 0){
                                addFeildHtml += " required ";
                            }
                            if(filed.getMinLength() > 0){
                                addFeildHtml += "minlength =\""+filed.getMinLength()+"\"";
                            }
                            if(filed.getMaxLength() > 0){
                                addFeildHtml += "minlength =\""+filed.getMaxLength()+"\"";
                            }
                            addFeildHtml += ">\n";
                        }else if(filed.getAttrType().equalsIgnoreCase("select")){
                            addFeildHtml +="<select " +
                                    "       id=\""+GemBeanUtils.lineToHump(filed.getAttrName())+"\" " +
                                    "       name=\""+GemBeanUtils.lineToHump(filed.getAttrName())+"\"" +
                                    //TODO: 设置布局
                                    "       class=\"block\" >";
                            String options = filed.getOptions();
                            String[] optionsArr = options.split(";");
                            if(optionsArr != null && optionsArr.length > 1){
                                for(int j = 0; j< optionsArr.length; j++){
                                    String[] option = optionsArr[j].split("=");
                                    if(option != null && option.length == 2){
                                        addFeildHtml += "<option value=\""+option[1]+"\">"+option[0]+"</option>";
                                    }
                                }
                            }
                            addFeildHtml +="</select>\n";
                        }else if(filed.getAttrType().equalsIgnoreCase("textarea")){
                            addFeildHtml += "<textarea id=\""+GemBeanUtils.lineToHump(filed.getAttrName())+"\" " +
                                    "name=\"+GemBeanUtils.lineToHump(filed.getAttrName())+\"></textarea>";
                        }
                        addFeildHtml += "   </td>\n" +
                                "</tr>";



                        //编辑页面
                        editFeildHtml += "<tr>\n" +
                                "   <td>"+filed.getComment()+"";
                        if(filed.getIsNull() == 0){
                            editFeildHtml += "\n<span class=\"span\">*</span>";
                        }
                        editFeildHtml += "   </td>\n";
                        if(filed.getAttrType().equalsIgnoreCase("text")
                                || filed.getAttrType().equalsIgnoreCase("number")
                                || filed.getAttrType().equalsIgnoreCase("checkbox")
                                || filed.getAttrType().equalsIgnoreCase("radio")
                                || filed.getAttrType().equalsIgnoreCase("email")
                                || filed.getAttrType().equalsIgnoreCase("tel")
                                || filed.getAttrType().equalsIgnoreCase("date")
                                || filed.getAttrType().equalsIgnoreCase("hidden")
                                || filed.getAttrType().equalsIgnoreCase("password")
                        ){
                            editFeildHtml +="       <input type=\""+filed.getAttrType()+"\" " +
                                    "       id=\""+GemBeanUtils.lineToHump(filed.getAttrName())+"\" " +
                                    "       name=\""+GemBeanUtils.lineToHump(filed.getAttrName())+"\"" +
                                    //TODO: 设置布局
                                    "       class=\"block\" ";
                            if(filed.getIsNull() == 0){
                                editFeildHtml += " required ";
                            }
                            if(filed.getMinLength() > 0){
                                editFeildHtml += "minlength =\""+filed.getMinLength()+"\"";
                            }
                            if(filed.getMaxLength() > 0){
                                editFeildHtml += "minlength =\""+filed.getMaxLength()+"\"";
                            }
                            editFeildHtml += "th:value=\"${editInfo."+GemBeanUtils.lineToHump(filed.getAttrName())+"}\">\n";
                            editFeildHtml += ">\n";
                        }else if(filed.getAttrType().equalsIgnoreCase("select")){
                            editFeildHtml +="<select " +
                                    "       id=\""+GemBeanUtils.lineToHump(filed.getAttrName())+"\" " +
                                    "       name=\""+GemBeanUtils.lineToHump(filed.getAttrName())+"\"" +
                                    //TODO: 设置布局
                                    "       class=\"block\" >";
                            String options = filed.getOptions();
                            String[] optionsArr = options.split(";");
                            if(optionsArr != null && optionsArr.length > 1){
                                for(int j = 0; j< optionsArr.length; j++){
                                    String[] option = optionsArr[j].split("=");
                                    if(option != null && option.length == 2){
                                        editFeildHtml += "<option th:selected=\"${editInfo."+GemBeanUtils.lineToHump(filed.getAttrName())+" eq "+option[1]+"}\" value=\""+option[1]+"\">"+option[0]+"</option>";
                                    }
                                }
                            }
                            editFeildHtml +="</select>\n";
                        }else if(filed.getAttrType().equalsIgnoreCase("textarea")){
                            editFeildHtml += "<textarea id=\""+GemBeanUtils.lineToHump(filed.getAttrName())+"\" " +
                                    "name=\"+GemBeanUtils.lineToHump(filed.getAttrName())+\" " +
                                    "th:text=\"${edit_user."+GemBeanUtils.lineToHump(filed.getAttrName())+"}\">" +
                                    "</textarea>";
                        }
                        editFeildHtml += "   </td>\n" +
                                "</tr>";
                    }


                    //列表页面：
                    if(filed.getIsVisit() == 1){
                        listFeildHtml += ",{field:'"+GemBeanUtils.lineToHump(filed.getAttrName())+"', title:'"+filed.getComment()+"'" +
                                ",sort: "+((filed.getIsSort() == 1)?true:false)+" " +
                                ",edit: '"+((filed.getEditType()==null)?null:filed.getEditType())+"'}\n            ";
                    }
                }
            }
            content = content
                    .replace("${entityCN}",upperEntity)
                    .replace("${pkName}",lowerPkName)
                    .replace("${pageW}",String.valueOf(pageW))
                    .replace("${pageH}",String.valueOf(pageH))
                    .replace("${addFeildHtml}",addFeildHtml)
                    .replace("${editFeildHtml}",editFeildHtml)
                    .replace("${listFeildHtml}",listFeildHtml);
        }

        boolean flag=false;
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(content.getBytes(GemConstant.Character.UTF8));
            fileOutputStream.close();
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    public boolean generateCode(TempParas tempParas,String output){
        String zipFilePath = output+"/code_"+tempParas.getId()+"/";

        try {
            Long id = tempParas.getId();
            String packageName = tempParas.getPackageName();
            String lowerEntity = tempParas.getEntityEn();
            String upperEntity = uppercaseFirst(lowerEntity);

            //spring boot中文件直接放在resources目录下
//            String tempControllerTxt = GemGenerate.readTxt(ResourceUtils.getFile("classpath:template/generate/java/contorller/TempController.txt"));
            String tempControllerTxt = new String(ByteStreams.toByteArray(new ClassPathResource("\\templates\\generate\\java\\contorller\\TempController.txt").getInputStream()));

//            String tempServiceTxt = GemGenerate.readTxt(ResourceUtils.getFile("classpath:template/generate/java/service/TempService.txt"));
            String tempServiceTxt = new String(ByteStreams.toByteArray(new ClassPathResource("\\templates\\generate\\java\\service\\TempService.txt").getInputStream()));

//            String tempServiceImplTxt = GemGenerate.readTxt(ResourceUtils.getFile("classpath:templates/generate/java/service/impl/TempServiceImpl.txt"));
            String tempServiceImplTxt = new String(ByteStreams.toByteArray(new ClassPathResource("\\templates\\generate\\java\\service\\impl\\TempServiceImpl.txt").getInputStream()));
//            String tempRepositoryTxt = GemGenerate.readTxt(ResourceUtils.getFile("classpath:templates/generate/java/repository/TempRepository.txt"));
            String tempRepositoryTxt = new String(ByteStreams.toByteArray(new ClassPathResource("\\templates\\generate\\java\\repository\\TempRepository.txt").getInputStream()));
//            String tempEntityTxt = GemGenerate.readTxt(ResourceUtils.getFile("classpath:templates/generate/java/model/po/TempEntity.txt"));
            String tempEntityTxt = new String(ByteStreams.toByteArray(new ClassPathResource("\\templates\\generate\\java\\model\\po\\TempEntity.txt").getInputStream()));
//            String tempEntityVoTxt = GemGenerate.readTxt(ResourceUtils.getFile("classpath:templates/generate/java/model/vo/TempEntityVo.txt"));
            String tempEntityVoTxt = new String(ByteStreams.toByteArray(new ClassPathResource("\\templates\\generate\\java\\model\\vo\\TempEntityVo.txt").getInputStream()));


            String javaCodePath = zipFilePath + "java/" +packageName+ "/";
            //文件目录
            String path1 = javaCodePath + "contorller/";
            String path2 = javaCodePath + "service/";
            String path3 = javaCodePath + "service/impl/";
            String path4 = javaCodePath + "repository/";
            String path5 = javaCodePath + "model/po/";
            String path6 = javaCodePath + "model/vo/";
            File zipFolder = new File(zipFilePath);
            if (zipFolder.exists()){
                zipFolder.delete();
                log.info(zipFolder+"文件夹路径存在，删除");
            }

            File folder1 = new File(path1);
            File folder2 = new File(path2);
            File folder3 = new File(path3);
            File folder4 = new File(path4);
            File folder5 = new File(path5);
            File folder6 = new File(path6);

            //文件夹路径不存在
            if (!folder1.exists() && !folder1.isDirectory()) {
                log.info("文件夹路径不存在，创建路径:" + path1);
                folder1.mkdirs();
            }
            //文件夹路径不存在
            if (!folder2.exists() && !folder2.isDirectory()) {
                log.info("文件夹路径不存在，创建路径:" + path2);
                folder2.mkdirs();
            }
            //文件夹路径不存在
            if (!folder3.exists() && !folder3.isDirectory()) {
                log.info("文件夹路径不存在，创建路径:" + path3);
                folder3.mkdirs();
            }
            //文件夹路径不存在
            if (!folder4.exists() && !folder4.isDirectory()) {
                log.info("文件夹路径不存在，创建路径:" + path4);
                folder4.mkdirs();
            }
            //文件夹路径不存在
            if (!folder5.exists() && !folder5.isDirectory()) {
                log.info("文件夹路径不存在，创建路径:" + path5);
                folder5.mkdirs();
            }
            //文件夹路径不存在
            if (!folder6.exists() && !folder6.isDirectory()) {
                log.info("文件夹路径不存在，创建路径:" + path6);
                folder6.mkdirs();
            }

            //生成文件
            File controller = new File(path1+upperEntity+"Controller.java");
            File service = new File(path2+upperEntity+"Service.java");
            File serviceImpl = new File(path3+upperEntity+"ServiceImpl.java");
            File repository = new File(path4+upperEntity+"Repository.java");
            File entity = new File(path5+upperEntity+".java");
            File entityVo = new File(path6+upperEntity+"Vo.java");

            writeTxtFile(JAVA,tempControllerTxt,tempParas,controller);
            writeTxtFile(JAVA,tempServiceTxt,tempParas,service);
            writeTxtFile(JAVA,tempServiceImplTxt,tempParas,serviceImpl);
            writeTxtFile(JAVA,tempRepositoryTxt,tempParas,repository);
            writeTxtFile(JAVA,tempEntityTxt,tempParas,entity);
            writeTxtFile(JAVA,tempEntityVoTxt,tempParas,entityVo);


            //=================HTML======================
//            String listHtmlTxt = GemGenerate.readTxt(ResourceUtils.getFile("classpath:templates/generate/html/list.txt"));
            String listHtmlTxt = new String(ByteStreams.toByteArray(new ClassPathResource("\\templates\\generate\\html\\list.txt").getInputStream()));
//            String addHtmlTxt = GemGenerate.readTxt(ResourceUtils.getFile("classpath:templates/generate/html/add.txt"));
            String addHtmlTxt = new String(ByteStreams.toByteArray(new ClassPathResource("\\templates\\generate\\html\\add.txt").getInputStream()));
//            String editHtmlTxt = GemGenerate.readTxt(ResourceUtils.getFile("classpath:templates/generate/html/edit.txt"));
            String editHtmlTxt = new String(ByteStreams.toByteArray(new ClassPathResource("\\templates\\generate\\html\\edit.txt").getInputStream()));


            String htmlCodePath = zipFilePath + "html/" +lowerEntity+ "/";
            File folderHtml = new File(htmlCodePath);
            //文件夹路径不存在
            if (!folderHtml.exists() && !folderHtml.isDirectory()) {
                log.info("文件夹路径不存在，创建路径:" + folderHtml);
                folderHtml.mkdirs();
            }

            if(tempParas.isAdd){
                //生成add
                File addHtml = new File(htmlCodePath+"add.html");
                writeTxtFile(HTML,addHtmlTxt,tempParas,addHtml);
            }

            if(tempParas.isEdit){
                //生成edit
                File editHtml = new File(htmlCodePath+"edit.html");
                writeTxtFile(HTML,editHtmlTxt,tempParas,editHtml);
            }
            //生成list
            File listHtml = new File(htmlCodePath+"list.html");
            writeTxtFile(HTML,listHtmlTxt,tempParas,listHtml);

            //开始压缩
            GemFilesUtils.compress(zipFilePath,output+"/code_"+id+".zip");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            log.info("执行完毕！");
        }
    }

    @Data
    @Builder
    public static class TempParas{

        private Long id;
        private String packageName;
        private String entityEn;
        private String entityCN;
        private String pkName;
        private String author;

        private int pageWidth;
        private int pageHeight;

        private boolean isAdd;
        private boolean isEdit;
        private boolean isDel;
        private boolean isQuery;

        private List<Filed> fileds;

        @Data
        @Builder
        public static class Filed{
            private Long id;
            private Long moduleId;
            private String attrName;
            private String comment;
            private String attrType;
            private String options;
            private Integer minLength;
            private Integer maxLength;
            private String editType;//列表支持编辑类型
            private Integer isNull;//是否允许为空
            private Integer isVisit;//列表显示
            private Integer isSort;//支持排序显示
            private Integer isSearch;//支持查询

            @Tolerate
            public Filed(){}
        }
        @Tolerate
        public TempParas(){}
    }

    public static void main(String[] args) {
        String aa = "txLog";
        String bb = uppercaseFirst(aa);
        String cc = aa.substring(0,1).toUpperCase().concat(aa.substring(1).toLowerCase());


        char[] chars = aa.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char)(chars[0] - 32);
        }
        System.out.println(new String(chars));

        String sss = GemBeanUtils.lineToHump(aa);
        System.out.println(sss);

    }


    public static List<String> getTemplates(){
        List<String> templates = new ArrayList<String>();
        templates.add("template/generate/velocity/java/Entity.java.vm");
        templates.add("template/generate/velocity/java/EntityVo.java.vm");
        templates.add("template/generate/velocity/java/Repository.java.vm");
        templates.add("template/generate/velocity/java/Service.java.vm");
        templates.add("template/generate/velocity/java/ServiceImpl.java.vm");
        templates.add("template/generate/velocity/java/Controller.java.vm");
//        templates.add("template/generate/velocity/menu.sql.vm");

        templates.add("template/generate/velocity/html/list.html.vm");
        templates.add("template/generate/velocity/html/add.html.vm");
        templates.add("template/generate/velocity/html/edit.html.vm");

        return templates;
    }

    /**
     * 生成代码
     */
    public static void generatorCode(Map<String, String> table,
                                     List<Map<String, String>> columns, ZipOutputStream zip) {
        //配置信息
        Configuration config = getConfig();
        boolean hasBigDecimal = false;
        //表信息
        Tables tables = new Tables();
        tables.setTableName(table.get("tableName" ));
        tables.setComments(table.get("tableComment" ));
        //表名转换成Java类名
        String className = tableToJava(tables.getTableName(), config.getStringArray("tablePrefix"));
        tables.setClassName(className);
        tables.setClassname(StringUtils.uncapitalize(className));

        //列信息
        List<Column> columsList = new ArrayList<>();
        for(Map<String, String> column : columns){
            Column columnEntity = new Column();
            columnEntity.setColumnName(column.get("columnName" ));
            columnEntity.setDataType(column.get("dataType" ));
            columnEntity.setComments(column.get("columnComment" ));
            columnEntity.setExtra(column.get("extra" ));

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), "unknowType" );
            columnEntity.setAttrType(attrType);
            if (!hasBigDecimal && attrType.equals("BigDecimal" )) {
                hasBigDecimal = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey" )) && tables.getPk() == null) {
                tables.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tables.setColumns(columsList);

        //没主键，则第一个字段为主键
        if (tables.getPk() == null) {
            tables.setPk(tables.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
        Velocity.init(prop);
        String mainPath = config.getString("mainPath" );
        mainPath = StringUtils.isBlank(mainPath) ? "com.gemframework" : mainPath;
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tables.getTableName());
        map.put("comments", tables.getComments());
        map.put("pk", tables.getPk());
        map.put("className", tables.getClassName());
        map.put("classname", tables.getClassname());
        map.put("pathName", tables.getClassname().toLowerCase());
        map.put("columns", tables.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("mainPath", mainPath);
        map.put("package", config.getString("package" ));
        map.put("moduleName", config.getString("moduleName" ));
        map.put("author", config.getString("author" ));
        map.put("email", config.getString("email" ));
        String datetime = GemDateUtils.format(new Date(), GemDateUtils.DATE_TIME_PATTERN);
        map.put("datetime",datetime);
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8" );
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tables.getClassName(), config.getString("package" ), config.getString("moduleName" ))));
                IOUtils.write(sw.toString(), zip, "UTF-8" );
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new GemException(-1,"渲染模板失败，表名：" + tables.getTableName()+",e="+ e);
            }
        }
    }


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "" );
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String[] tablePrefixArray) {
        if(null != tablePrefixArray && tablePrefixArray.length>0){
            for(String tablePrefix : tablePrefixArray){
                tableName = tableName.replace(tablePrefix, "");
            }
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties" );
        } catch (ConfigurationException e) {
            throw new GemException(-1,"获取配置文件失败，"+ e);
        }
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String moduleName) {
        String packagePath = File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }

        if (template.contains("Entity.java.vm" )) {
            return packagePath + "model" + File.separator +"po"+ File.separator + className + ".java";
        }

        if (template.contains("EntityVo.java.vm" )) {
            return packagePath + "model" + File.separator +"vo"+ File.separator + className + "Vo.java";
        }

        if (template.contains("Repository.java.vm" )) {
            return packagePath + "repository" + File.separator + className + "Repository.java";
        }

        if (template.contains("Service.java.vm" )) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm" )) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm" )) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("list.html.vm" )) {
            return "html" + File.separator + className + File.separator + "list.html";
        }

        if (template.contains("edit.html.vm" )) {
            return "html" + File.separator + className + File.separator + "edit.html";
        }

        if (template.contains("add.html.vm" )) {
            return "html" + File.separator + className + File.separator + "add.html";
        }

        return null;
    }

}
