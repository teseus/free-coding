import tensorflow as tf
hello = tf.constant('Hello tests')
sess = tf.Session()
print(sess.run(hello))
print(tf.__version__)