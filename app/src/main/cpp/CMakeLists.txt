cmake_minimum_required(VERSION 3.22.1)
project(localai_engine)

add_library(localai_engine SHARED llama_jni.cpp)
find_library(log-lib log)
target_link_libraries(localai_engine ${log-lib})
