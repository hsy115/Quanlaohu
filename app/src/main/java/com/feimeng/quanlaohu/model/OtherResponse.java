/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feimeng.quanlaohu.model;

import java.io.Serializable;

/**
 * 解析基本模型
 * Created by hsy on 2017/8/2.
 */
public class OtherResponse<T,T2> implements Serializable {


    public int result;
    public int code;
    public String message;
    public T data;
    public T2 other;

    @Override
    public String toString() {
        return "OtherResponse{" +
                "result=" + result +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", other=" + other +
                '}';
    }
}
